package Survery;

public class infoDto {
	private int age;
	private String religion;
	
	public infoDto() {
		
	}
	
	public infoDto(String religion, int age) {
		super();
		this.age = age;
		this.religion = religion;
	}

	@Override
	public String toString() {
		return religion +" : "+ age + "명";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
}
