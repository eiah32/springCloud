package com.eiah.util;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.NotEmpty;

@GroupSequence({GroupA.class, GroupB.class})
public class DefaultGroup {

	@NotEmpty(message = "{username.empty}", groups = GroupA.class)
	private String username;
	
	@NotEmpty(message = "{password.empty}", groups = GroupB.class)
	private String password;
}
