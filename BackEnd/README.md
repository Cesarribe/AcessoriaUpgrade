#  Acessoria de Corrida - Gestão de Treinos

Bem-vindo ao **Acessoria de Corrida**, um sistema de gestão de treinos esportivos que oferece monitoramento, relatórios e estatísticas de desempenho para atletas.  

##  Visão Geral
O Acessoria de Corrida foi foi desenvolvido para oferecer uma abordagem inovadora no gerenciamento de treinos, atletas e evolução de desempenho para. Uma plataforma que combina tecnologia e eficiência para ajudar os usuários a alcançar seus objetivos de forma segura, eficiente e personalizada .

 Destaques da Plataforma:Gamificação dos treinos – Ranking dos melhores desempenhos diários, Gamificação dos treinos – Ranking dos melhores desempenhos diários, semanais, mensais e anuais para aumentar o engajamento.  Rede social integrada (a ser implementada) – Compartilhamento de conquistas e – Compartilhamento de conquistas e interações entre atletas.  Dicas e frases motivacionais – Estímulos para superar limites e manter – Estímulos para superar limites e manter a disciplina. Painel de objetivos – Definição de metas individuais com acompanhamento do professor. – Definição de metas individuais com acompanhamento do professor. Planejamento seguro de treinos​ do condicionamento físico para – Análise do condicionamento físico para treinos personalizados.

Na parte técnica, o sistema utiliza autenticação segura via JWT , consultas otimizadas e relatórios detalhados , garantindo uma experiência fluida para atletas e administradores .
##  Tecnologias Utilizadas
- **Java + Spring Boot** – Backend robusto e escalável  
- **Spring Security + JWT** – Autenticação segura  
- **Hibernate + JPA** – Persistência no banco de dados  
- **PostgreSQL/MySQL** – Banco de dados relacional  
- **Lombok** – Simplificação de código  
- **Postman** – Testes de API  

##  Funcionalidades
✔ **Autenticação de usuários** – Proteção com JWT  
✔ **Cadastro e gerenciamento de treinos** – Distância, duração, atleta responsável  
✔ **Relatórios de desempenho** – Estatísticas e evolução  
✔ **Controle de papéis e permissões** – `ROLE_ADMIN`, `ROLE_USER`  
✔ **Otimização de consultas** – Eficiência nos relatórios  
✔ **Integração com banco de dados** – Uso de JPA/Hibernate  

##  Endpoints Principais
Aqui estão os **endpoints** mais importantes da API:  

### Autenticação
- `POST /api/auth/login` – Login de usuário  
- `POST /api/auth/register` – Cadastro de usuário  

### Treinos e Relatórios
- `POST /api/treinos` – Criar treino  
- `GET /api/treinos/{id}` – Buscar treino específico  
- `PUT /api/treinos/{id}` – Atualizar treino  
- `DELETE /api/treinos/{id}` – Remover treino  
- `GET /api/admin/treinos/relatorio` – Relatório geral de treinos  

##  Como Rodar o Projeto
1 - **Clone o repositório**  
```bash
git clone https://github.com/seu-usuario/acessoria-corrida.git

2 - Configure o banco de dados no application.properties

3 -  Execute uma aplicação

4 - execute: mvn spring-boot:run

##  **Melhorias Futuras**

 Painel interativo para administradores

- Notificações de registro de ritmo

- Análises gráficas de evolução

- Integração com GPS para rastreamento (strava e Garmin)

