package main.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

//**set customer table
 
@Entity
@Table(name = "customer")
public class Customer { 
	
	public enum PostalCode {
		基隆市_仁愛區200,基隆市_信義區201,台北市_中正區100,台北市_大同區103,新北市_萬里區207,新北市_板橋區220,桃園市_中壢區320,桃園市_平鎮區324,新竹市_東區300,新竹縣_竹北市302
		,新竹縣_湖口鄉303,苗栗縣_竹南鎮350,苗栗縣_頭份市351,台中市_中區400,台中市_東區401,彰化縣_彰化市500,彰化縣_芬園鄉502,南投縣_南投市540,南投縣_中寮鄉541,雲林縣_斗南鎮630
		,雲林縣_大埤鄉631,嘉義市_東區600,嘉義縣_番路鄉602,嘉義縣_梅山鄉603,台南市_中西區700,台南市_東區701,高雄市_新興區800,高雄市_前金區801,屏東縣_屏東市900,屏東縣_三地門901
		,台東縣_臺東市950,台東縣_綠島鄉951,花蓮縣_花蓮市970,花蓮縣_新城鄉971,宜蘭縣_宜蘭市260,宜蘭縣_頭城鎮261,澎湖縣_馬公市880,澎湖縣_西嶼鄉881,金門縣_金沙鎮890,金門縣_金湖鎮891
		,連江縣_南竿鄉209,連江縣_北竿鄉210
	}
	
	public enum City {
		基隆市,台北市,新北市,桃園市,新竹市,新竹縣,苗栗縣,台中市,彰化縣,南投縣,雲林縣,嘉義市,嘉義縣,台南市,高雄市,
		屏東縣,台東縣,花蓮縣,宜蘭縣,澎湖縣,金門縣,連江縣
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long customerId;

	@NotBlank(message = "{customer.companyName.notblank}")
	@Size(min = 2, max = 20, message = "{customer.name.size}")
	@Column(length = 20)
	@Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "{customer.companyName.type}")
	private String companyName;
	
	@NotBlank(message = "{customer.contactName.notblank}")
	@Size(min = 2, max = 20, message = "{customer.name.size}")
	@Column(length = 20)
	@Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "{customer.name.type}")
	private String contactName;
	
//	@Pattern(regexp = "^[0-9]{5}$", message = "{customer.postalcode.pattern}")
	@Column(length = 20)
	private String postalCode;
	
	@Pattern(regexp = "^09\\d{8}", message= "{customer.phonenumber.pattern}")     
	@Column(length = 10)
	private String phoneNumber;
	
	@NotBlank(message = "{customer.contactName.notblank}")
	@Size(min = 2, max = 20, message = "{customer.name.size}")
	@Column(length = 30)
	@Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "{customer.name.type}")
	private String district;
	
	private String city;
	
//	@OneToOne(mappedBy="customer")
//	private User user;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "all_inclusive")
	private boolean allInclusive = false;
	
//	@ManyToMany
//	@JoinTable(name = "customer2user",
//			   joinColumns = @JoinColumn(name = "customerId"),
//			   inverseJoinColumns = @JoinColumn(name = "userId"))
//	private List<User> users;
	
	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_date")
	private Date createDate;
    
	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "update_date")
	private Date updateDate;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isAllInclusive() {
		return allInclusive;
	}

	public void setAllInclusive(boolean allInclusive) {
		this.allInclusive = allInclusive;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
