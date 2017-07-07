package IBM.Agent;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class AgentHome {

	Sessions ses;
	public AgentHome(String ID) {
		// TODO Auto-generated constructor stub

		ses=ExecutionInstance.getSession(ID);
	}
	
	public void Home() {
		
		

	}
	
	
	
}
