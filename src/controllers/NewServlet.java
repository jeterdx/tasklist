package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.task;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // CSRF対策
        request.setAttribute("_token", request.getSession().getId());

        //おまじないとしてのインスタンスを生成、これは_form.jspを経由しているため、リクエストスコープにtaskがないとエラーになってしまう。
        //なので画面表示時のエラー回避のため、とりあえず “文字数0のデータ” をフォームに渡すために、以下のインスタンスを生成している。
        request.setAttribute("task", new task());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
        rd.forward(request, response);
     }
}