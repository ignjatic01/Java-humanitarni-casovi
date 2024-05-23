
public class Red {

	public static Red glava = null;
	public static Red rep = null;
	public Red next;
	Student st;
	
	public Red() {
		next = null;
		
	}
	
	public synchronized void dodajStudenta(Student s) {
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
	
	public synchronized Student ukloniStudenta() {
		
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