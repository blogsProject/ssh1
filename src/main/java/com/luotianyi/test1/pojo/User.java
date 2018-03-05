package com.luotianyi.test1.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 用户角色表
 * 
 * @title: User.java
 * @package com.shuyuntu.report.entity
 * @description: TODO
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月29日 上午11:29:34
 * @version 1.0
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"uername"})})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5198614306054277891L;
	private Integer id;
	private String uername;
	private String password;

	public User() {
		super();
	}

	public User(Integer id, String uername, String password) {
		super();
		this.id = id;
		this.uername = uername;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 16, nullable = false)
	public String getUername() {
		return uername;
	}

	public void setUername(String uername) {
		this.uername = uername;
	}

	@Column(length = 255, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uername=" + uername + ", password=" + password + "]";
	}

}
