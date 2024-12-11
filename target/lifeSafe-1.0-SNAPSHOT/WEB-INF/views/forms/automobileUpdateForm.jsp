<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Automobile Update Form</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/automobile.css">
</head>
<body>
<div class="demo-page">
  <main class="demo-page-content">
    <section>
      <h1>Update Automobile Insurance</h1>
      <form action="${pageContext.request.contextPath}/devis/${insurance.id}/update/automobile" method="post">
        <input type="hidden" name="type" value="Automobile"/>
        <div class="nice-form-group">
          <label for="driverAge">Driver Age</label>
          <input type="number" id="driverAge" name="driverAge" value="${insurance.driverAge}" required/>
        </div>

        <div class="nice-form-group">
          <label for="car">Select Car</label>
          <select id="car" name="car.id" required>
            <c:forEach var="car" items="${cars}">
              <option value="${car.id}" ${car.id == insurance.car.id ? 'selected' : ''}>${car.brand} ${car.model} (${car.type})</option>
            </c:forEach>
          </select>
        </div>

        <div class="nice-form-group">
          <label for="carUse">Car Use</label>
          <select id="carUse" name="carUse" required>
            <c:forEach var="carUse" items="${carUses}">
              <option value="${carUse}" ${carUse == insurance.carUse ? 'selected' : ''}>${carUse}</option>
            </c:forEach>
          </select>
        </div>

        <div class="nice-form-group">
          <label for="drivingHistory">Driving History</label>
          <input type="text" id="drivingHistory" name="drivingHistory" value="${insurance.drivingHistory}" required/>
        </div>

        <div class="nice-form-group">
          <label for="sinisterTime">Last time you had an accident:</label>
          <input type="date" id="sinisterTime" name="lastAccidentDate" value="${insurance.lastAccidentDate}" required/>
        </div>

        <button class="animated-button" type="submit">
          <span class="text">UPDATE INSURANCE</span>
        </button>
      </form>
    </section>
  </main>
</div>
</body>
</html>
