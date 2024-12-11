<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update Housing Insurance</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/automobile.css">
</head>
<body>
<div class="demo-page">
    <main class="demo-page-content">
        <section>
            <h1>Update Housing Insurance</h1>
            <form action="${pageContext.request.contextPath}/devis/${devis.id}/update/housing" method="post">
                <input type="hidden" name="type" value="Housing"/>

                <div class="nice-form-group">
                    <label for="homeValue">Valeur du Logement (en €)</label>
                    <input type="number" id="homeValue" name="homeValue" value="${insurance.homeValue}" required/>
                </div>

                <div class="nice-form-group">
                    <label for="homeType">Type de Logement</label>
                    <select id="homeType" name="homeType" required>
                        <option value="Maison" ${insurance.homeType == 'Maison' ? 'selected' : ''}>Maison</option>
                        <option value="Appartement" ${insurance.homeType == 'Appartement' ? 'selected' : ''}>Appartement</option>
                        <option value="Studio" ${insurance.homeType == 'Studio' ? 'selected' : ''}>Studio</option>
                    </select>
                </div>

                <div class="nice-form-group">
                    <label for="location">Emplacement</label>
                    <input type="text" id="location" name="location" value="${insurance.location}" required/>
                </div>

                <div class="nice-form-group">
                    <label for="securitySystem">Système de Sécurité</label>
                    <select id="securitySystem" name="securitySystem" required>
                        <option value="Alarme" ${insurance.securitySystem == 'Alarme' ? 'selected' : ''}>Alarme</option>
                        <option value="Caméra" ${insurance.securitySystem == 'Caméra' ? 'selected' : ''}>Caméra</option>
                        <option value="Aucun" ${insurance.securitySystem == 'Aucun' ? 'selected' : ''}>Aucun</option>
                    </select>
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
