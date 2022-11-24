package ru.javarush.module3.quest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.module3.quest.service.GameService;
import ru.javarush.module3.quest.util.PropertiesLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "init-servlet", value = "/start")
public class InitServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            String appPath = super.getServletContext().getRealPath("");
            gameService = GameService.getInstance();
            gameService.init(appPath);
        } catch (Exception e) {
            logger.error("Problems with InitServlet init(): " + e.getMessage());
        }

        logger.info("GameService initialized.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession();
            String userSessionId = session.getId();
            String userName = request.getParameter("userName");
            int firstQuestionId = 1;

            if (!gameService.userIsPresent(userSessionId)) {
                gameService.addNewUser(userSessionId, userName);
                logger.info("New User added: " + userName + " SessionID: " + userSessionId);
            }

            session.setAttribute("userName", gameService.getUserName(userSessionId));
            session.setAttribute("userScore", gameService.getUserScore(userSessionId));
            session.setAttribute("question", gameService.findQuestionById(firstQuestionId));
            session.setAttribute("answers", gameService.findAnswersByQuestionId(firstQuestionId));

            response.sendRedirect(request.getContextPath() + "/questgame.jsp");
            logger.info("QuestGame is initialized and running.");

        } catch (Exception e) {
            logger.error("Problems with InitServlet doGet(): " + e.getMessage());
        }
    }

}