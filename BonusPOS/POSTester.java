import java.io.IOException;

public class POSTester {
	
	public static void main(String[] args) throws IOException{
		ProgramOfStudy pos = new ProgramOfStudy();
		pos.addCourse(new Course("CS", 101, "Introduction to Programming", "A-"));
		pos.addCourse(new Course("ARCH", 305, "Building Analysis", "A"));
		pos.addCourse(new Course("GER", 210, "Intermediate German"));
		pos.addCourse(new Course("CS", 320, "Computer Architecture"));
		pos.addCourse(new Course("THE", 201, "The Theatre Experience"));
		Course arch = pos.find("CS", 320);
		pos.addCourseAfter(arch, new Course("CS", 321, "Operating Systems"));
		Course german = pos.find("GER", 210);
		pos.replace(german, new Course("FRE", 110, "Beginning French", "B+"));
		Course theatre = pos.find("THE", 201);
		theatre.setGrade("A-");
		System.out.println(pos);
		//pos.save("ProgramOfStudy");   
		}
}


