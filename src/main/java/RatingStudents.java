import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class RatingStudents {

	public static void main(String[] args) {

		List<Assignments> allAssignments= new ArrayList<Assignments>();
		Assignments a1= new Assignments("Ananth", "Electro Fields","test_1", "21-Jul-16", 100);
		Assignments a2= new Assignments("Bhagath", "Electro Fields","test_1", "21-Jul-16", 78);
		Assignments a3= new Assignments("Chaya", "Electro Fields","test_1", "21-Jul-16", 68);
		Assignments a4= new Assignments("Ananth", "Electro Fields","project_1", "24-Jul-16", 100);
		Assignments a5= new Assignments("Ananth", "Computing Techniques","test_1", "29-Jul-16", 86);
		Assignments a6= new Assignments("Ananth", "Electro Fields","quiz_1", "24-Jul-16", 100);
		Assignments a7= new Assignments("Ananth", "Electro Fields","quiz_2", "24-Jul-16", 100);
		Assignments a8= new Assignments("Ananth", "Electro Fields","lab_1", "24-Jul-16", 100);
		Assignments a9= new Assignments("Ananth", "Electro Fields","test_2", "24-Jul-16", 100);
		Assignments a10= new Assignments("Esharath", "Electro Fields","test_1", "24-Jul-16", 87);
		Assignments a11= new Assignments("Esharath", "Electro Fields","project_1", "24-Jul-16", 90);


		allAssignments.add(a1);
		allAssignments.add(a4);
		allAssignments.add(a5);
		allAssignments.add(a6);
		allAssignments.add(a7);
		allAssignments.add(a8);
		allAssignments.add(a9);
		allAssignments.add(a3);
		allAssignments.add(a2);
		allAssignments.add(a10);
		allAssignments.add(a11);

		Map<String, Integer>assignmentCategWeightage= new HashMap<String, Integer>();
		assignmentCategWeightage.put("test", 40);
		assignmentCategWeightage.put("project", 30);
		assignmentCategWeightage.put("quiz", 20);
		assignmentCategWeightage.put("lab", 10);

        
		Scanner ch= new Scanner(System.in);
		Scanner sc= new Scanner(System.in);
		int choice;
        do {
		System.out.println("Enter your choice: 1.Calculate the student average score per assignment category & overall rating for assigned subject(s).");
		System.out.println("2. Calculate Subject average score per assignment category & overall rating for assigned student(s).");
		System.out.println("3. Exit");

		choice= ch.nextInt();
		if (choice==1) {
			System.out.println("Enter the student name to calculate his rating");
			String studentName= sc.nextLine();
			StudentRatingInEachSubject(studentName,allAssignments, assignmentCategWeightage);
			
		}
		else if (choice==2){
			System.out.println("Enter the subject name to calculate all students' rating in that subject");
			String subjectName= sc.nextLine();
			SubjectRatingofAllStudents(subjectName,allAssignments, assignmentCategWeightage);

		}
		else {
			System.out.println("Invalid Entry, please try again");
		}}while(choice!=3);
        
			ch.close();
			sc.close();
	}
	
	public static void StudentRatingInEachSubject(String studName, List<Assignments> allassgmnts, Map<String, Integer>weightage) {
		
		Map<String, Map<String, List<Integer>>> subjectsAssignedToStudentAndAssignments= new HashMap<>();  //Creates a map containing subject names as keys and values as a hashmap containing assignmentcategs as key and a list of points submitted 
		//System.out.println(studName);//TEST
		ListIterator<Assignments> li= allassgmnts.listIterator();
		while(li.hasNext()) {
			int a=0;
			Assignments currentasg=li.next();
			//System.out.println(currentasg.studentName);//TEST
			if(currentasg.studentName.equals(studName)) {
				if (!subjectsAssignedToStudentAndAssignments.containsKey(currentasg.subject)) {
					subjectsAssignedToStudentAndAssignments.put(currentasg.subject, new HashMap<>());}
					for(int i=0; i<currentasg.assignmentCategory.length(); i++)
					{
						if(currentasg.assignmentCategory.charAt(i)=='_') {
							a=i;
							break;
						}
					}
					String correctAssgctg=currentasg.assignmentCategory.substring(0,a);
					if(!subjectsAssignedToStudentAndAssignments.get(currentasg.subject).containsKey(correctAssgctg))
					subjectsAssignedToStudentAndAssignments.get(currentasg.subject).put(correctAssgctg,new ArrayList<Integer>());
					subjectsAssignedToStudentAndAssignments.get(currentasg.subject).get(correctAssgctg).add(currentasg.points);
					
				}
					
				}
double overallrating;
		for (String sub :subjectsAssignedToStudentAndAssignments.keySet()) {
			overallrating=0;
			System.out.println("-------------");
			System.out.println(sub);
			System.out.println("-------------");
			for(String categ: subjectsAssignedToStudentAndAssignments.get(sub).keySet()) {
				ListIterator<Integer> ci= subjectsAssignedToStudentAndAssignments.get(sub).get(categ).listIterator();
				double total=0;
				
				while(ci.hasNext()) {
					double num=ci.next();
					total= total+num;
				}
double average= total/(subjectsAssignedToStudentAndAssignments.get(sub).get(categ).size());
double score= average*weightage.get(categ)/100;
overallrating+=score;
System.out.println("Score in Assignment Category, "+ categ+ ":"+ score);

		}
			System.out.println("Overall Rating: "+ overallrating);
			System.out.println();
			System.out.println();
		}
		
	
	}
	public static void SubjectRatingofAllStudents(String subjectName, List<Assignments> allassgmnts, Map<String, Integer>weightage) {
		Map<String, Map<String, List<Integer>>> allAssignmentsOfSubjectSubmittedByStudents= new HashMap<>();  //Creates a map containing Student names as keys and values as a hashmap containing assignmentcategs as key and a list of points submitted 
		ListIterator<Assignments> li= allassgmnts.listIterator();
		while(li.hasNext()) {
			int a=0;
			Assignments currentasg=li.next();
			//System.out.println(currentasg.studentName);//TEST
			if(currentasg.subject.equals(subjectName)) {
				if (!allAssignmentsOfSubjectSubmittedByStudents.containsKey(currentasg.studentName)) {
					allAssignmentsOfSubjectSubmittedByStudents.put(currentasg.studentName, new HashMap<>());}
					for(int i=0; i<currentasg.assignmentCategory.length(); i++)
					{
						if(currentasg.assignmentCategory.charAt(i)=='_') {
							a=i;
							break;
						}
					}
					String correctAssgctg=currentasg.assignmentCategory.substring(0,a);
					if(!allAssignmentsOfSubjectSubmittedByStudents.get(currentasg.studentName).containsKey(correctAssgctg))
						allAssignmentsOfSubjectSubmittedByStudents.get(currentasg.studentName).put(correctAssgctg,new ArrayList<Integer>());
					allAssignmentsOfSubjectSubmittedByStudents.get(currentasg.studentName).get(correctAssgctg).add(currentasg.points);
					
				}
		}
double overallrating;
for (String studName :allAssignmentsOfSubjectSubmittedByStudents.keySet()) {
	overallrating=0;
	System.out.println("-------------");
	System.out.println(studName);
	System.out.println("-------------");
	for(String categ: allAssignmentsOfSubjectSubmittedByStudents.get(studName).keySet()) {
		ListIterator<Integer> ci= allAssignmentsOfSubjectSubmittedByStudents.get(studName).get(categ).listIterator();
		double total=0;
		
		while(ci.hasNext()) {
			double num=ci.next();
			total= total+num;
		}
double average= total/(allAssignmentsOfSubjectSubmittedByStudents.get(studName).get(categ).size());
double score= average*weightage.get(categ)/100;
overallrating+=score;
System.out.println("Score in Assignment Category, "+ categ+ ":"+ score);

}
	System.out.println("Overall Rating: "+ overallrating);
	System.out.println();
	System.out.println();
}
}
}