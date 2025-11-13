package autoEcole;

import com.google.gson.Gson;

import autoEcole.Controller.CandidatController;

public class Main {

	public static void main(String[] args) {
		        Gson gson = new Gson();
		        String json = gson.toJson("Hello world!");
		        System.out.println(json);
		        
		CandidatController c = new CandidatController();
		c.init();

	}	

}
