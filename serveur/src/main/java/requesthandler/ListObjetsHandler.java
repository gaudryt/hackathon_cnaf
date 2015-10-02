package requesthandler;



import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class ListObjetsHandler implements RequestHandler{

	
    // Reports the method names of the handled requests
	public String[] handledRequests() {
	
	    return new String[]{"getDate", "getTime"};
	}
	
	
	// Processes the requests
	public JSONRPC2Response process(JSONRPC2Request req, MessageContext ctx) {
	
	    if (req.getMethod().equals("getListeObjets")) {
		
		String num = (String) req.getNamedParams().get("num");
		
		return new JSONRPC2Response(genererSlides(num), req.getID());

        }
	    else {
	    
	        // Method name not supported
		
		return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, req.getID());
        }
    }
	
	private String[] genererSlides(String num){
		String[] rep = new String[10];
		rep[0] = "0;INF;Ceci est un test visible.;Ceci est un test caché;01/01/2010";
		rep[1] = "1;QUE;Quelle est la couleur du cheval blanc d'Henry 4 ?;Blanc;01/02/2010";
		rep[2] = "2;DEM;Rouen;Paris;01/03/2010";
		rep[3] = "3;MES;Le saviez-vous ? Aujourd'hui a lieu le hackathon de la cnaf !;nill;01/04/2010";
		rep[4] = "4;INF;Vous pouvez recevoir 1000 euros pour cette année;nill;01/05/2010";
		rep[5] = "5;QUE;Qu'est ce qui est petit, jaune et qui fait peur ?;Un poussin avec une mitraillette;01/06/2010";
		rep[6] = "6;DEM;Terre;Mars;01/07/2010";
		rep[7] = "7;MES;Le saviez vous ? 1/2 des français ont droit à la CAF;Ca fait beaucoup;01/08/2010";
		rep[8] = "8;INF;Votre fiston fête ses 3 ans;Kado;01/09/2010";
		rep[9] = "9;QUE;Quel est l'age de la branche famille des allocations ?;60;01/10/2010";
		return rep;
	}
}