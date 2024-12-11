<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Contract</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .upload-container {
            text-align: center;
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .upload-header {
            font-size: 1.5em;
            margin-bottom: 15px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .custom-file-upload {
            margin-top: 20px;
            width: 116px;
        }
    </style>
</head>
<body>
<div class="upload-container">
    <h2 class="upload-header">Add Permis Files if you want contract</h2>
    <form action="${pageContext.request.contextPath}/contract/add?devisId=${devisId}" method="post"
          enctype="multipart/form-data">
        <input type="hidden" name="devisId" value="${devisId}">
        <div class="container">
            <div class="folder">
                <div class="front-side">
                    <div class="tip"></div>
                    <div class="cover"></div>
                </div>
                <div class="back-side cover"></div>
            </div>
            <label class="custom-file-upload">
                <input class="titleInput" type="file" id="document" name="document" accept=".pdf,.jpg,.jpeg,.png" required/>
                Choose a file
            </label>
        </div>
        <div style="margin-top: 20px">
            <a href="${pageContext.request.contextPath}/devis">
                <button type="button" class="cancelBtn">
                    cancel
                </button>
            </a>
            <button class="cancelBtn" type="submit">Submit Contract</button>
        </div>
    </form>

</div>
</body>
</html>
