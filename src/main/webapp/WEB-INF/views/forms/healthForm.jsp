<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/automobile.css">
</head>
<body>
<div class="demo-page">
    <main class="demo-page-content">
        <section>
            <div class="href-target" id="input-types"></div>
            <h1>
                Health add form
            </h1>
            <form action="${pageContext.request.contextPath}/health/addInsurance" method="post">
                <div class="nice-form-group">
                    <label for="clientAge">Client Age</label>
                    <input type="number" id="clientAge" name="age" required/>
                </div>

                <div class="nice-form-group">
                    <label for="coverType">Cover Type</label>
                    <select id="coverType" name="medicalCoverageType" required>
                        <c:forEach var="coverType" items="${coverTypes}">
                            <option value="${coverType}">${coverType}</option>
                        </c:forEach>
                    </select>
                </div>

                <label for="healthState">Health State:</label>
                <input type="checkbox" id="chronicIllness" name="chronicIllness" value="true">
                <label for="chronicIllness">Chronic Illness</label>

                <button class="animated-button" type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" class="arr-2" viewBox="0 0 24 24">
                        <path
                                d="M16.1716 10.9999L10.8076 5.63589L12.2218 4.22168L20 11.9999L12.2218 19.778L10.8076 18.3638L16.1716 12.9999H4V10.9999H16.1716Z"
                        ></path>
                    </svg>
                    <span class="text">ADD INSURANCE</span>
                    <span class="circle"></span>
                    <svg xmlns="http://www.w3.org/2000/svg" class="arr-1" viewBox="0 0 24 24">
                        <path
                                d="M16.1716 10.9999L10.8076 5.63589L12.2218 4.22168L20 11.9999L12.2218 19.778L10.8076 18.3638L16.1716 12.9999H4V10.9999H16.1716Z"
                        ></path>
                    </svg>
                </button>
            </form>
        </section>
    </main>
</div>
</body>
</html>
