package pairpro7;

import dao.BattleDAO;
import dao.MonsterDAO;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import model.Account;
import model.Battle;
//import model.Login;
import model.Monster;

@SuppressWarnings("serial")
@WebService(name = "Pairpro7")
public class Pairpro7Servlet extends HttpServlet {
    Random rd = new Random();
    
    public static int count = 1;
    public int userHP = rd.nextInt(10) + 1;
    public  int userAtk = rd.nextInt(3) + 1;
    Monster monster = new Monster();
    public int monsterHP ;
    public int monsterAtk;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MonsterDAO dao = new MonsterDAO();
        Monster monster = dao.testmonster(); 
        if(monster == null) {
            response.sendRedirect("");
            response.setContentType("text/plain");
    		response.getWriter().println("Hello, world");
        }else {
            
            HttpSession session = request.getSession();
            session.setAttribute("monster",monster);
            monsterHP = monster.getHp();
            monsterAtk = monster.getAtk();
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request,response);
        	//response.setContentType("text/plain");
    		//response.getWriter().println(monsterAtk);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();
            List<Battle> battleList = (List<Battle>)application.getAttribute("battleList");
            List<String> textList = new ArrayList<String>();
        HttpSession session = request.getSession();
            //Login loginUser = (Login)session.getAttribute("loginUser");
            //String name = loginUser.getUserId();
        	String name = "うめこ";
        	int userHP = 50;
        	int userAtk = 5;
            Monster monster = (Monster)session.getAttribute("monster");
            String text = "";
            BattleDAO dao = new BattleDAO();
            String win = "";
            String lose = "";
            Battle battle;

                
            while(userHP > 0 || monsterHP > 0) {
                text = name + "の攻撃";
                out.print(text);
                textList.add(text);
                battle = new Battle(name,text);
                //dao.create(battle);
                application.setAttribute("battleList",battleList);
                battleList = dao.testBattle();     
                request.setAttribute("battleList", battleList);
                
                out.print( monster.getName()+"は"+userAtk+"ダメージくらった" );
                //textList.add(text);
                //out.println(text);
                
                battle = new Battle(name,text);
                //dao.create(battle);
                application.setAttribute("battleList",battleList);
                battleList = dao.testBattle();     
                request.setAttribute("battleList", battleList);
           
                monsterHP = monsterHP- userAtk;
                out.print("残りは"+monsterHP+"だ。\n");
                out.println( "<br>" );
                //out.println(monsterHP);
                if(monsterHP <= 0) {
                    text = name+"さんの勝ちです!";
                    //textList.add(text);
                    out.println( "<br>" ); 
                    out.println( "<br>" );
                    out.println(text);
                    out.println( "<br>" );
                    //battle = new Battle(name,text);
                    //dao.create(battle);
                    //application.setAttribute("battleList",battleList);
                    request.setAttribute("win","勝ち");
                    break;
                    //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/win.jsp");
                    //dispatcher.forward(request, response);
                }
                
                
                text = monster.getName()+"の攻撃";
                textList.add(text);
                
                out.println(text);
                
                battle = new Battle(name,text);
                //dao.create(battle);
                application.setAttribute("battleList",battleList);
                battleList = dao.testBattle();     
                request.setAttribute("battleList", battleList);
                //for(int i = 1;i < battleList.size();i++)
                //      out.println(battleList.get(i));
                
                text = name+"は"+monsterAtk+"ダメージくらった" ;
                //textList.add(text);
                out.println(text);
                //text = text1+text2+text3+text4;
                battle = new Battle(name,text);
                //dao.create(battle);
                application.setAttribute("battleList",battleList);
                battleList = dao.testBattle();     
                request.setAttribute("battleList", battleList);
                //for(int i = 1;i < battleList.size();i++)
                //out.println(battleList.get(i));
                userHP = userHP - monsterAtk;
                out.print("残りは"+userHP+"だ。");
                out.println( "<br>" );
                if(userHP <= 0) {
                    text = name+"さんの負けです";
                    //textList.add(text);
                    out.println( "<br>" ); 
                    out.println( "<br>" );
                    out.println(text);
                    out.println( "<br>" );
                    battle = new Battle(name,text);
                    //dao.create(battle);
                    application.setAttribute("battleList",battleList);
                    request.setAttribute("lose","負け");
                    break;

                }
                    text = "残りのHP  "+name+" : "+userHP+", "+monster.getName()+" : "+monsterHP;
                    textList.add(text);
                    battle = new Battle(name,text);
                    dao.create(battle);
                
            }
            battleList = dao.findAll();     
            request.setAttribute("battleList", battleList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
