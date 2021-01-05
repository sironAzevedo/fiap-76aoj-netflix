package com.netflix.series.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.netflix.series.model.enums.SerieLikeEnum;

@JsonInclude(Include.NON_EMPTY)
public class SerieLikeDTO extends SerieUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Informe yes ou no")
	private SerieLikeEnum liked;

	public SerieLikeDTO() {
		super();
	}

	public SerieLikeEnum getLiked() {
		return liked;
	}

	public void setLiked(SerieLikeEnum liked) {
		this.liked = liked;
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
