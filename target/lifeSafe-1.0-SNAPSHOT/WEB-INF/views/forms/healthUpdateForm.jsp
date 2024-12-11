<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Update Health Insurance</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/automobile.css">
</head>
<body>
<div class="demo-page">
  <main class="demo-page-content">
    <section>
      <h1>Update Health Insurance</h1>
      <form action="${pageContext.request.contextPath}/devis/${insurance.id}/update/health" method="post">
        <input type="hidden" name="type" value="Health"/>

        <div class="nice-form-group">
          <label for="clientAge">Client Age</label>
          <input type="number" id="clientAge" name="age" value="${insurance.age}" required/>
        </div>

        <div class="nice-form-group">
          <label for="coverType">Cover Type</label>
          <select id="coverType" name="medicalCoverageType" required>
            <c:forEach var="coverType" items="${coverTypes}">
              <option value="${coverType}" ${coverType == insurance.medicalCoverageType ? 'selected' : ''}>${coverType}</option>
            </c:forEach>
          </select>
        </div>

        <label for="healthState">Health State:</label>
        <input type="checkbox" id="chronicIllness" name="chronicIllness" value="true" ${insurance.chronicIllness ? 'checked' : ''}>
        <label for="chronicIllness">Chronic Illness</label>

        <button class="animated-button" type="submit">
          <span class="text">UPDATE INSURANCE</span>
        </button>
      </form>
    </section>
  </main>
</div>
</body>
</html>
