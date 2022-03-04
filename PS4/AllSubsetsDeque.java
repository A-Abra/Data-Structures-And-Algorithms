import java.util.LinkedList;
import java.util.Deque;

public class AllSubsetsDeque {
	public static void main(String[] args) {
		Deque<CState> q = new LinkedList<CState>();
		LinkedList<CState> l = new LinkedList<CState>();
		CState start = new CState("", "boa");
//		CState start = new CState("", "njwhoaurg");
		q.addFirst(start);
		//q.addLast(start);
		while (!q.isEmpty()) {
			CState next = q.removeFirst();
			if (next.suff().length() > 0) {
				String newPrefix = next.pre() + next.suff().charAt(0);
				String newSuffix = next.suff().substring(1);
				System.out.println(newPrefix);
				CState n1 = new CState(newPrefix, newSuffix);
				CState n2 = new CState(next.pre(), newSuffix);
				q.addFirst(n1);
				q.addFirst(n2);
				//q.addLast(n2);
				//q.addLast(n1);
			}
		}
	}
}
