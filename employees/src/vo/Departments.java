package vo;

public class Departments {
	// 뱐수 생성
	private String deptNo;
	private String deptName;
	
	// get, set
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	// to String
	@Override
	public String toString() {
		return "Departments [deptNo=" + deptNo + ", deptName=" + deptName + "]";
	}
}
