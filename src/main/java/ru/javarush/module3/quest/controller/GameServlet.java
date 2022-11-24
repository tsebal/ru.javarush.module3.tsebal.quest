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
import java.io.IOException;

@WebServlet(name = "game-servlet", value = "/questgame")
public class GameServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            gameService = GameService.getInstance();
        } catch (Exception e) {
            logger.error("Problems with GameServlet init(): " + e.getMessage());
        }
        logger.info("GameServlet initialized.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {

            String userSessionId = request.getSession().getId();
            String answerIdParam = request.getParameter("id");
            if (answerIdParam == null || answerIdParam.isBlank()) {
                response.sendRedirect(request.getContextPath() + "/questgame.jsp");
                return;
            }

            int answerId = Integer.parseInt(request.getParameter("id"));
            int nextQuestionId = gameService.findNextQuestionIdByAnswerId(answerId);

            switch (nextQuestionId) {
                case 0:
                    request.getSession().setAttribute("restart", true);
                    break;
                case -1:
                    request.getSession().setAttribute("end", true);
                default:
                    gameService.incrementUserScore(userSessionId);
            }
            if (nextQuestionId == 0) {
                gameService.resetUserScore(userSessionId);
                logger.info("QuestGame: Game Over.");
            }

            setSessionAttributes(request, nextQuestionId);
            response.sendRedirect(request.getContextPath() + "/questgame.jsp");

        } catch (Exception e) {
            logger.error("Problems with GameServlet doPost(): " + e.getMessage());
        }
    }

    private void setSessionAttributes(HttpServletRequest request, int nextQuestionId) {
        HttpSession session = request.getSession();
        String userSessionId = session.getId();
        session.setAttribute("userScore", gameService.getUserScore(userSessionId));
        session.setAttribute("question", gameService.findQuestionById(nextQuestionId));
        session.setAttribute("answers", gameService.findAnswersByQuestionId(nextQuestionId));

        logger.info("GameServlet: from method setSessionAttributes - completed: " + userSessionId +
                 " Next questionId: " + nextQuestionId);
    }
}