package com.netflix.authorization.model.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.netflix.authorization.model.enums.PerfilEnum;

public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
    private PerfilEnum name;

	public RoleDTO() {
		super();
	}
	
	public RoleDTO(PerfilEnum name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PerfilEnum getName() {
		return name;
	}

	public void setName(PerfilEnum name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
