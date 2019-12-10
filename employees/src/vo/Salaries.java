package vo;

public class Salaries {
	// 변수 생성
	private Employees employees;
	private int salary;
	private String fromDate;
	private String toDate;
	
	// 읽기와 수정이 가능한 get,set 메소드 생성
	// get은 return값을 이용해 읽기만 가능
	// set은 return값은 없지만 매개변수를 통해 값 변환
	
	public Employees getEmployees() {
		return employees;
	}
	public void setEmployees(Employees employees) {
		this.employees = employees;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		return "Salaries [employees=" + employees + ", salary=" + salary + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ "]";
	}
}
