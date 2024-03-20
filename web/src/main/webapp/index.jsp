<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/19/2024
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>TMS - ADMIN</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
    <div class="header-container">
        <span>Traffic Management System - Admin Panel</span>

        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(new Date());
        %>

        <span><%= formattedDate %></span>
    </div>
    <hr>

    <table>
        <thead>
            <tr>
                <th class="table-side-header header-1">Time Period</th>
                <th>00:00-06:00</th>
                <th>06:00-12:00</th>
                <th>12:00-18:00</th>
                <th>18:00-00:00</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th class="table-side-header header-2">Avg Speed (KmPH) : Route A1</th>
                <td id="r1_t1">0.0</td>
                <td id="r1_t2">0.0</td>
                <td id="r1_t3">0.0</td>
                <td id="r1_t4">0.0</td>
            </tr>
            <tr>
                <th class="table-side-header header-2">Avg Speed (KmPH) : Route A6</th>
                <td id="r2_t1">0.0</td>
                <td id="r2_t2">0.0</td>
                <td id="r2_t3">0.0</td>
                <td id="r2_t4">0.0</td>
            </tr>
        </tbody>
    </table>

    <hr>
    <div class="row">
        <div class="col-4">
            <h4>Route View</h4>
            <span style="font-size: 0.8rem;">* Red Circle Indicates Traffic Intersections.</span>
            <br>
            <img src="assets/images/route.png" width="350px" height="350px">
        </div>
        <div class="col-8">
            <%-- Route Details - Start --%>
            <h4>Route Details</h4>
            <span>Start Point (A1): 7.495028, 80.352666</span><br>
            <span>End Point (A1): 7.489311, 80.360923</span><br>
            <span>Start Point (A6): 7.492702, 80.364822</span><br>
            <span>End Point (A6): 7.484192, 80.359437</span><br>
            <span>Traffic Intersection (T1): 7.490468, 80.358728</span><br>
            <span>Traffic Intersection (T2): 7.488809, 80.361947</span>
            <%-- Route Details - End --%>

            <%-- Traffic Patterns - Start --%>
            <h4>Traffic Patterns</h4>
            <table>
                <thead>
                    <tr>
                        <th class="table-side-header header-1">Time Period</th>
                        <th>00:00-06:00</th>
                        <th>06:00-12:00</th>
                        <th>12:00-18:00</th>
                        <th>18:00-00:00</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th class="table-side-header header-2">Congestion Level : Route A1</th>
                        <td id="c_r1_t1">--</td>
                        <td id="c_r1_t2">--</td>
                        <td id="c_r1_t3">--</td>
                        <td id="c_r1_t4">--</td>
                    </tr>
                    <tr>
                        <th class="table-side-header header-2">Congestion Level : Route A6</th>
                        <td id="c_r2_t1">--</td>
                        <td id="c_r2_t2">--</td>
                        <td id="c_r2_t3">--</td>
                        <td id="c_r2_t4">--</td>
                    </tr>
                </tbody>
            </table>
            <%-- Traffic Patterns - End --%>

            <%-- Traffic Color Light Status - Start --%>
            <h4>Traffic Color Light Status</h4>
            <div class="row">
                <div class="col-6" style="text-align: center">
                    <table class="traffic-intersection-table">
                        <thead>
                            <tr>
                                <th colspan="3">Traffic Intersection 1 (T1)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div id="tli_1_red" class="status-round"></div>
                                </td>
                                <td>
                                    <div id="tli_1_yellow" class="status-round"></div>
                                </td>
                                <td>
                                    <div id="tli_1_green" class="status-round"></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-6" style="text-align: center">
                    <table class="traffic-intersection-table">
                        <thead>
                        <tr>
                            <th colspan="3">Traffic Intersection 2 (T2)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div id="tli_2_red" class="status-round"></div>
                            </td>
                            <td>
                                <div id="tli_2_yellow" class="status-round"></div>
                            </td>
                            <td>
                                <div id="tli_2_green" class="status-round"></div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <%-- Traffic Color Light Status - End --%>
        </div>
    </div>

    <script src="assets/js/script.js"></script>
</body>
</html>
