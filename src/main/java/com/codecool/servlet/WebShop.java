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

@WebServlet(name = "simpleServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class WebShop extends HttpServlet {
    private WebShopServlet webShop = new WebShopServlet();
    private List<Item> itemsList = webShop.init();
    private ArrayList<Item> cart = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();
        String title = "WebShop Practice";
        HttpSession session = request.getSession();

        out.println(
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +

                "<table style=\"border-collapse: collapse\">\n" );
        for(Item item: itemsList) {
            out.println("<tr >" +
                    "<td style=\"border: 1px solid #000\">" + item.getId() + item.getName() + "</td>" +
                    "<td style=\"border: 1px solid #000\">" + item.getPrice() + " USD" + "</td>" +
                    "<td > " +
                    "<form action=\"/\" method=\"post\" id=\"btnform" + item.getId() + "\">" +
                    "<input type=\"hidden\" name=\"itemname\" value=" + item.getName() + "></input>" +
                    "<input type=\"hidden\" name=\"itemid\" value=" + item.getId() + "></input>" +
                    "<input type=\"hidden\" name=\"itemprice\" value=" + item.getPrice() + "></input>" +
                    "<button type=\"submit\" >Add</button>" +
                    "</form>" +
                    "</td>" +
                    "<td >" +
                    "<form action=\"/\" method=\"post\" id=\"removeform" + item.getId() + "\">" +
                    "<input type=\"hidden\" name=\"removeitemid\" value=" + item.getId() + "></input>" +
                    "<button type=\"submit\" >Remove</button>" +
                    "</form>" +
                    "</td>" +
                    "</tr>");
        }
        out.println(
                "</table>\n" +
                "<br>" +
                "<div><a href=\"/shopping-cart\">Check Shopping Cart</a></div>" +
                "</body></html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String itemName = request.getParameter("itemname");
        String itemId = request.getParameter("itemid");
        String itemPrice = request.getParameter("itemprice");
        String removedItemId = request.getParameter("removeitemid");
        List<Item> removedItems = new ArrayList<>();

        if(itemId!=null && itemName!=null && itemPrice!=null) {
            Item item = new Item(Integer.parseInt(itemId), itemName, Double.parseDouble(itemPrice));
            this.cart.add(item);
        }

        handleRemove(removedItemId, removedItems);

        HttpSession session = request.getSession(false);
        session.setAttribute("cart", this.cart);
        response.sendRedirect("/");
    }

    protected void handleRemove(String removedItemId, List removedItems) {
        if(removedItemId!=null) {
            for (Item item: this.cart) {
                if(item.getId()==Integer.parseInt(removedItemId)) {
                    removedItems.add(item);
                }
            }
        }

        if(removedItems.size() > 0) {
            this.cart.removeAll(removedItems);
        }
    }


}
