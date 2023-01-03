package com.revature.models;

import java.util.Objects;

public class ChangePassword {

	public String changePasswordUsername;
	public String changePasswordPassword;
	public String changePasswordNewPassword;
	
	
	public ChangePassword(String changePasswordUsername, String changePasswordPassword,
			String changePasswordNewPassword) {
		super();
		this.changePasswordUsername = changePasswordUsername;
		this.changePasswordPassword = changePasswordPassword;
		this.changePasswordNewPassword = changePasswordNewPassword;
	}
	public ChangePassword() {
		super();
	}
	@Override
	public String toString() {
		return "ChangePassword [changePasswordUsername=" + changePasswordUsername + ", changePasswordPassword="
				+ changePasswordPassword + ", changePasswordNewPassword=" + changePasswordNewPassword + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(changePasswordNewPassword, changePasswordPassword, changePasswordUsername);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePassword other = (ChangePassword) obj;
		return Objects.equals(changePasswordNewPassword, other.changePasswordNewPassword)
				&& Objects.equals(changePasswordPassword, other.changePasswordPassword)
				&& Objects.equals(changePasswordUsername, other.changePasswordUsername);
	}
	public String getChangePasswordUsername() {
		return changePasswordUsername;
	}
	public void setChangePasswordUsername(String changePasswordUsername) {
		this.changePasswordUsername = changePasswordUsername;
	}
	public String getChangePasswordPassword() {
		return changePasswordPassword;
	}
	public void setChangePasswordPassword(String changePasswordPassword) {
		this.changePasswordPassword = changePasswordPassword;
	}
	public String getChangePasswordNewPassword() {
		return changePasswordNewPassword;
	}
	public void setChangePasswordNewPassword(String changePasswordNewPassword) {
		this.changePasswordNewPassword = changePasswordNewPassword;
	}
	
	
	
}
