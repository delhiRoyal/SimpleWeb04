package com.epam.domain;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = -7975999732971764267L;

	String name;
	String description;
	String numberOfPages;
	String category;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getNumberOfPages() {
		return numberOfPages;
	}

	public String getCategory() {
		return category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberOfPages == null) ? 0 : numberOfPages.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfPages == null) {
			if (other.numberOfPages != null)
				return false;
		} else if (!numberOfPages.equals(other.numberOfPages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", description=" + description + ", numberOfPages=" + numberOfPages
				+ ", category=" + category + "]";
	}

}
