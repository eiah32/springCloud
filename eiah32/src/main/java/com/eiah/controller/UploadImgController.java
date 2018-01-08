package com.eiah.controller;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.eiah.domain.User;
import com.eiah.iframework.util.FileUploadUtil;
import com.eiah.iframework.util.IDGenerator;
import com.eiah.service.UserService;
import com.eiah.util.CheckFileTypeUtil;
import com.eiah.util.DataTimeUtil;
import com.eiah.util.DataTimeUtil.TimeFormat;
import com.eiah.util.DeleteFileUtil;

@Controller
@RequestMapping("/uploadImg")
public class UploadImgController {
	@Autowired
	private UserService userService;

	// 缩放头像的宽度和高度 单位px
	@Value("${imgWidth:400}")
	private int imgWidth;
	@Value("${imgHeight:300}")
	private int imgHeight;
	// 临时存放文件目录
	@Value("${tmpImgDirectory}")
	private String tmpImgDirectory;
	// 保存头像目录
	@Value("${saveHeadImgDirectory}")
	private String saveHeadImgDirectory;

	@RequestMapping(value = "/uploadHeadImage", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> uploadHeadImage(HttpServletRequest request,
			@RequestParam(value = "x") String x, @RequestParam(value = "y") String y,
			@RequestParam(value = "h") String h, @RequestParam(value = "w") String w,
			@RequestParam(value = "imgFile") MultipartFile imageFile) throws Exception {
		Map<String, Object> results = new HashMap<>();
		try {
			// 工程根目录
			String realPath = request.getSession().getServletContext().getRealPath("/");
			realPath = realPath.substring(0, realPath.indexOf("\\webapp"));
			String fileName = imageFile.getOriginalFilename();
			String imageFormat = fileName.substring(fileName.lastIndexOf(".") + 1);
			if (imageFile != null && FileUploadUtil.allowUpload(imageFile.getContentType())) {
				String rename = DataTimeUtil.getCurrentDateTime(TimeFormat.LONG_DATE_PATTERN_WITH_MILSEC_NULL) + IDGenerator.getFixLenthString(6);
				String srcPath = rename + "_src." + imageFormat;
				String cutPath = rename + "_cut." + imageFormat;
				String imgParentDirectory = realPath + saveHeadImgDirectory;
				String imgChildDirectory = FileUploadUtil.createStoreDirectory(imgParentDirectory, srcPath);
				String baseDirectory = imgParentDirectory + File.separator + imgChildDirectory;
				File dir = new File(baseDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File file = new File(baseDirectory, srcPath);
				imageFile.transferTo(file);
				int imageX = Integer.parseInt(x);
				int imageY = Integer.parseInt(y);
				int imageH = Integer.parseInt(h);
				int imageW = Integer.parseInt(w);
				cropImage(baseDirectory + File.separator + srcPath, baseDirectory + File.separator + cutPath, imageX, imageY, imageH, imageW, imageFormat, imageFormat);
				User user = (User) request.getSession().getAttribute("user");
				user.setSrcImgPath(imgChildDirectory + File.separator + srcPath);
				user.setCutImgPath(imgChildDirectory + File.separator + cutPath);
				int state = userService.updateUserImgPath(user);
				if (state == 1) {
					user = userService.findUserById(user.getId());
					request.getSession().setAttribute("user", user);
				}
			}
			results.put("state", "success");
			results.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			results.put("state", "fail");
			results.put("msg", "保存失败");
		}
		return results;
	}

	/**
	 * 缩放图片至特定的宽高大小
	 * 
	 * @param src
	 *            源文件目录
	 * @param dest
	 *            缩放后保存目录
	 * @param w
	 *            缩放后图片的宽度 px
	 * @param h
	 *            缩放后图片的高度 px
	 * 
	 *            created by eiah on 2017-08-26
	 */
	public boolean zoomImage(String src, String dest, int w, int h) {
		double wr = 0, hr = 0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		try {
			BufferedImage bufImg = ImageIO.read(srcFile); // 读取图片
			@SuppressWarnings("static-access")
			Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);// 设置缩放目标图片模板

			wr = w * 1.0 / bufImg.getWidth(); // 获取缩放比例
			hr = h * 1.0 / bufImg.getHeight();

			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);

			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile); // 写入缩减后的图片
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
	
	/**
	 * 对图片裁剪，并把裁剪新图片保存
	 * 
	 * @param srcPath
	 *            读取源图片路径
	 * @param toPath
	 *            写入图片路径
	 * @param x
	 *            剪切起始点x坐标
	 * @param y
	 *            剪切起始点y坐标
	 * @param width
	 *            剪切宽度
	 * @param height
	 *            剪切高度
	 * @param readImageFormat
	 *            读取图片格式
	 * @param writeImageFormat
	 *            写入图片格式
	 * @throws Exception
	 */
	public void cropImage(String srcPath, String cutPath, int x, int y, int width, int height, String readImageFormat,
			String writeImageFormat) {
		FileInputStream fis = null;
		ImageInputStream iis = null;
		try {
			// 读取图片文件
			fis = new FileInputStream(srcPath);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(readImageFormat);
			ImageReader reader = (ImageReader) it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(fis);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			// 定义一个矩形
			Rectangle rect = new Rectangle(x, y, width, height);
			// 提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			// 保存新图片
			ImageIO.write(bi, writeImageFormat, new File(cutPath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("图片裁剪异常！");
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (iis != null)
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 文件上传
	 */
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadImage(@RequestParam(value = "imgFile") MultipartFile imageFile) {
		String[] imgType = { "jpg", "png", "gif", "bmp" };
		boolean isImg = false;
		JSONObject results = new JSONObject();
		String fileName = imageFile.getOriginalFilename();
		// 临时存放文件目录
		String filePath = tmpImgDirectory + fileName;
		File dest = new File(filePath);
		// 判断文件父目录是否存在,若不存在，则创建目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}

		try {
			// 保存文件
			imageFile.transferTo(dest);

			// 获取文件类型,判断是否图片类型
			String fileType = CheckFileTypeUtil.getFileType(filePath.replaceAll("/", "\\\\"));
			if (fileType != null) {
				for (String str : imgType) {
					if (isImg = str.equals(fileType)) {
						break;
					}
				}
				if (!isImg) {
					results.put("status", "fail");
					results.put("message", "图片类型错误");
					return results;
				}
			} else {
				results.put("status", "fail");
				results.put("message", "非图片类型文件");
				return results;
			}

			// 缩放图片比例, 缩放完成后覆盖原来的图片
			if (zoomImage(filePath, filePath, imgWidth, imgHeight)) {
				results.put("message", fileName);
				results.put("status", "success");
			} else {
				// 删除文件,提示图片类型错误或缩放图片比例失败
				DeleteFileUtil.deleteFile(filePath);
				results.put("status", "fail");
				results.put("message", "图片尺寸不合适，建议300px*400px大小");
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.put("status", "fail");
			results.put("message", "图片上传失败");
			return results;
		}
	}

}
