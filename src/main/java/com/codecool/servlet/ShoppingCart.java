package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "anotherServlet", urlPatterns = {"/shopping-cart"}, loadOnStartup = 2)
public class ShoppingCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        List<Item> itemCart = new ArrayList<>();
        if(session.getAttribute("cart") instanceof List) {
            itemCart = (ArrayList<Item>) session.getAttribute("cart");
        }

        PrintWriter out = response.getWriter();

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append("<div>");
            buffer.append("<a href=\"/another?link_id=" + i + "\">");
            buffer.append("Hello " + i + ". link:");
            buffer.append("</a>");
            buffer.append("</div>");
        }

        double sumOfPrice = 0;
        for (int i = 0; i < itemCart.size(); i++) {
            sumOfPrice += itemCart.get(i).getPrice();
        }

        out.println(
                "<html>\n" +
                        "<head><title>Another page</title></head>\n" +
                        "<body>\n" +
                        "<h1>Shopping Cart</h1>" +
                        "<table style=\"border-collapse: collapse\">\n" );
        for(Item item: itemCart) {
            out.println("<tr >" +
                    "<td style=\"border: 1px solid #000\">" + item.getId() + item.getName() + "</td>" +
                    "<td style=\"border: 1px solid #000\">" + item.getPrice() + " USD" + "</td>" +
                    "</tr>");
        }
        out.println(
                "</table>\n" +
                        "<br>" +
                        "<h1>Sum of Price: " + sumOfPrice + "</h1>" +
                        "</body></html>"
        );
    }
}
