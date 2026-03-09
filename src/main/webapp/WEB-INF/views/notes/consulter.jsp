<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulter une Note</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .header {
            background-color: #2c3e50;
            color: white;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 30px;
        }
        .form-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #34495e;
        }
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        select:focus {
            outline: none;
            border-color: #3498db;
        }
        .btn-submit {
            background-color: #3498db;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        .btn-submit:hover {
            background-color: #2980b9;
        }
        .btn-retour {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #95a5a6;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn-retour:hover {
            background-color: #7f8c8d;
        }
        .info-text {
            background-color: #e8f4f8;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            border-left: 4px solid #3498db;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>${titre}</h1>
        </div>
        
        <div class="info-text">
            <p><strong>Comment ça marche ?</strong> Sélectionnez un étudiant et une matière pour calculer sa note réelle selon les règles de gestion (différence entre notes, paramètres par matière).</p>
        </div>
        
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/notes/consulter" method="post">
                <div class="form-group">
                    <label for="idCandidat">Étudiant *</label>
                    <select id="idCandidat" name="idCandidat" required>
                        <option value="">Sélectionner un étudiant</option>
                        <c:forEach items="${candidats}" var="candidat">
                            <option value="${candidat.id}">${candidat.nom} ${candidat.prenom}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="idMatiere">Matière *</label>
                    <select id="idMatiere" name="idMatiere" required>
                        <option value="">Sélectionner une matière</option>
                        <c:forEach items="${matieres}" var="matiere">
                            <option value="${matiere.id}">${matiere.libelle}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <button type="submit" class="btn-submit">Calculer la Note Réelle</button>
            </form>
            
            <a href="${pageContext.request.contextPath}/notes" class="btn-retour">← Retour</a>
        </div>
    </div>
</body>
</html>