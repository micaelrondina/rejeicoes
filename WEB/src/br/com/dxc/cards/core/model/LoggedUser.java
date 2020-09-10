package br.com.dxc.cards.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*
@SessionScoped
@Inject*/
public class LoggedUser implements Serializable {
	private static final long serialVersionUID = -1375483618444622606L;
	private String name;
	private String token;
	private List<String> tasks = new ArrayList<String>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LoggedUser other = (LoggedUser) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "LoggedUserBean [name=" + this.name + ", tasks=" + this.tasks + "]";
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
