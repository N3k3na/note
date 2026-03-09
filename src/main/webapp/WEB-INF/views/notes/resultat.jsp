<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Résultat du Calcul</title>
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
        .result-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 40px;
            text-align: center;
        }
        .info-section {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
            text-align: left;
        }
        .info-item {
            margin-bottom: 10px;
            font-size: 18px;
        }
        .info-label {
            font-weight: bold;
            color: #34495e;
            width: 120px;
            display: inline-block;
        }
        .info-value {
            color: #2c3e50;
        }
        .note-result {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 40px;
            border-radius: 8px;
            margin: 30px 0;
        }
        .note-result h2 {
            font-size: 24px;
            margin-bottom: 20px;
            opacity: 0.9;
        }
        .note-value {
            font-size: 72px;
            font-weight: bold;
            line-height: 1;
        }
        .note-value span {
            font-size: 24px;
            opacity: 0.8;
        }
        .btn-primary {
            display: inline-block;
            padding: 15px 30px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s;
        }
        .btn-primary:hover {
            background-color: #2980b9;
        }
        .btn-secondary {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #95a5a6;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn-secondary:hover {
            background-color: #7f8c8d;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>${titre}</h1>
        </div>
        
        <div class="result-card">
            <c:if test="${not empty noteReelle}">
                <div class="info-section">
                    <div class="info-item">
                        <span class="info-label">Étudiant :</span>
                        <span class="info-value">${candidat.nom} ${candidat.prenom}</span>
                    </div>
                    <div class="info-item">
                        <span class="info-label">Matière :</span>
                        <span class="info-value">${matiere.libelle}</span>
                    </div>
                </div>
                
                <div class="note-result">
                    <h2>Note Réelle Calculée</h2>
                    <div class="note-value">
                        <fmt:formatNumber value="${noteReelle}" pattern="#0.00"/> <span>/20</span>
                    </div>
                </div>
                
                <p style="color: #7f8c8d; margin: 20px 0;">
                    Cette note a été calculée selon les règles de gestion définies pour la matière.
                </p>
                
                <a href="${pageContext.request.contextPath}/notes/consulter" class="btn-primary">
                    Nouvelle Consultation
                </a>
            </c:if>
            
            <c:if test="${empty noteReelle}">
                <p style="color: #e74c3c;">Aucune note trouvée pour cet étudiant dans cette matière.</p>
                <a href="${pageContext.request.contextPath}/notes/consulter" class="btn-primary">
                    Réessayer
                </a>
            </c:if>
            
            <a href="${pageContext.request.contextPath}/notes" class="btn-secondary">
                ← Retour à l'accueil
            </a>
        </div>
    </div>
</body>
</html>