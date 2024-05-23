
public class Red {

	public static Red glava = null;
	public static Red rep = null;
	public Red next;
	Student st;
	
	public Red() {
		next = null;
		
	}
	
	public void dodajStudenta(Student s) {
		synchronized(lock1) {
			
		}
		Red cvor = new Red();
		cvor.st = s;
		if(glava == null) {
			glava = cvor;
			rep = cvor;
		} else {
			rep.next = cvor;
			rep = cvor;
		}
		
		
	}
	
	public Student ukloniStudenta() {
		synchronized(lock1) {
			
		}
		if(glava != null) {
			
			Student elem = glava.st;
			if(glava.next != null) {
				glava = glava.next;
			} else {
				glava = null;
				rep = null;
			}
			return elem;
		}
		return null;
		
	}

}