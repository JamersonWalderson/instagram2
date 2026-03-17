#!/bin/bash

# Script para executar testes de performance com Gatling

echo "🚀 Iniciando testes de performance com Gatling"
echo "=============================================="

# Verifica se a aplicação está rodando
echo "📡 Verificando se a aplicação está rodando em http://localhost:8080..."
if ! curl -s http://localhost:8080/api/posts > /dev/null; then
    echo "❌ Aplicação não está rodando! Inicie a aplicação primeiro:"
    echo "   ./mvnw spring-boot:run"
    exit 1
fi

echo "✅ Aplicação está rodando!"

# Compila o projeto
echo "🔨 Compilando o projeto..."
./mvnw clean compile test-compile

if [ $? -ne 0 ]; then
    echo "❌ Falha na compilação!"
    exit 1
fi

echo "✅ Projeto compilado com sucesso!"

# Executa o teste simples com 50 usuários
echo "🎯 Executando teste de performance com 50 usuários..."
echo "=================================================="

./mvnw gatling:test -Dgatling.simulationClass=com.example.instagram2.performance.SimplePostSimulation

echo ""
echo "📊 Relatório gerado em: target/gatling/results/"
echo "🌐 Abra o relatório HTML no navegador para ver os resultados!"

# Opção para executar teste avançado
echo ""
echo "🔥 Para executar teste avançado (múltiplos cenários):"
echo "   ./mvnw gatling:test -Dgatling.simulationClass=com.example.instagram2.performance.PostApiSimulation"
