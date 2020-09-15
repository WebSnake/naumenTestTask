package ru.salnikov.NaumenTestTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
        
		PrintWriter writer = response.getWriter();
		try {
			DBConnection dbConn = new DBConnection();
	        switch(command) {
	        	case "create": {
	        		String heading = request.getParameter("heading").trim();
	        		String note = request.getParameter("note");
	        		dbConn.insert("INSERT INTO NEWS(heading, note) VALUES('" + heading + "', '" + note + "')");
	        		writer.println(outNotes("SELECT * FROM NEWS"));
	        		break;
	        	}
	        	case "showAll": {
	        		writer.println(outNotes("SELECT * FROM NEWS"));
	        		break;
	        	}
	        	
	        	case "delete": {
	        		String id = request.getParameter("id");
	        		dbConn.delete("DELETE FROM NEWS WHERE id='" + id + "'");
	        		writer.println(outNotes("SELECT * FROM NEWS"));
	        		break;
	        	}
	        	
	        	case "showNote": {
	        		String id = request.getParameter("id");
	        		dbConn.select("SELECT * FROM NEWS WHERE id=" + id);
	        		writer.println(dbConn.selected);
	        		break;
	        	}
	        	
	        	case "search": {
	        		String keyWord = request.getParameter("keyWord").trim();
	        		dbConn.selectForSearch("SELECT * FROM NEWS");
	        		Map<Integer, String> res = new HashMap<>();
	        		//String result = "<table align=\"center\"";
	        		for(Map.Entry<Integer, String> item : dbConn.headingResult.entrySet()) {
	        			if (item.getValue().contains(keyWord) && !res.containsKey(item.getKey())) {
	        				res.put(item.getKey(), item.getValue());
	        			}
	        		}
	        		
	        		for(Map.Entry<Integer, String> item : dbConn.noteResult.entrySet()) {
	        			if (item.getValue().contains(keyWord) && !res.containsKey(item.getKey())) {
	        				res.put(item.getKey(), item.getValue());
	        			}
	        		}
	        		if (res.isEmpty()) {
	        			writer.println("Ничего не найдено!");
	        		} else {
	        			String query = "SELECT * FROM NEWS WHERE ID IN(";
		        		for(Map.Entry<Integer, String> item : res.entrySet()) {
		        			query += item.getKey() + ",";
		        		}
		        		query = query.substring(0, query.length() - 1) + ")";
		        		dbConn.headingResult.clear();
		        		dbConn.noteResult.clear();
		        		writer.println(outNotes(query));
	        		}
	        		break;
	        	}
	        }

        } finally {
            writer.close();  
        }
	}
	
	public String outNotes(String query) {
		DBConnection dbConn = new DBConnection();
		dbConn.selectAll(query);
		String res = "<table align=\"center\"";
		for(String str: dbConn.selectResult) {
			res += str;
		}
		res += "</table>";
		dbConn.selectResult.clear();
		return res;
	}
}
