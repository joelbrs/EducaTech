INSERT INTO tb_usuario (cpf, nome, email, senha, tipo_usuario) VALUES ('12345678900', 'Educatech', 'admin@educatech.com', '12345', 2)
INSERT INTO tb_usuario (cpf, nome, email, senha, tipo_usuario) VALUES ('98765432100', 'Joel da Silva Pereira Filho', 'aluno@educatech.com', '12345', 1)

INSERT INTO tb_curso (titulo, descricao, carga_horaria, imagem) VALUES ('Docker', 'Você vai aprender como criar, administrar ambientes isolados através de containers com o Docker', 80, 'https://cdn.vuetifyjs.com/images/john.jpg')
INSERT INTO tb_curso (titulo, descricao, carga_horaria, imagem) VALUES ('Kubernetes', 'Aprenda a orquestrar e gerenciar aplicativos em contêineres com Kubernetes', 90, 'https://cdn.vuetifyjs.com/images/john.jpg');
INSERT INTO tb_curso (titulo, descricao, carga_horaria, imagem) VALUES ('Python', 'Domine uma das linguagens mais populares e versáteis do mundo da programação', 100, 'https://cdn.vuetifyjs.com/images/john.jpg');
INSERT INTO tb_curso (titulo, descricao, carga_horaria, imagem) VALUES ('React', 'Aprenda a criar interfaces de usuário dinâmicas e modernas com ReactJS', 120, 'https://cdn.vuetifyjs.com/images/john.jpg');
INSERT INTO tb_curso (titulo, descricao, carga_horaria, imagem) VALUES ('Machine Learning', 'Explore o mundo do aprendizado de máquina e suas aplicações práticas', 150, 'https://cdn.vuetifyjs.com/images/john.jpg');

INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Começando do zero', 'Introdução aos conceitos básicos do Docker', 1, 1);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Iniciando com Docker', 'Configuração inicial e uso do Docker', 2, 1);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Trabalhando com Imagens', 'Manipulação e customização de imagens no Docker', 3, 1);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Networks', 'Gerenciamento de redes no ambiente Docker', 4, 1);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Colocando em Prática', 'Exercícios práticos para aplicar os conhecimentos adquiridos', 5, 1);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Otimizando Imagens', 'Estratégias para otimizar o tamanho das imagens Docker', 6, 1);

INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Introdução ao Kubernetes', 'Visão geral e conceitos fundamentais do Kubernetes', 1, 2);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Gerenciamento de Pods', 'Criação, execução e escalonamento de pods no Kubernetes', 2, 2);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Services e Networking', 'Configuração e uso de serviços e redes no Kubernetes', 3, 2);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Implantações e Rollouts', 'Estratégias de implantação e atualização de aplicações', 4, 2);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Monitoramento e Logging', 'Ferramentas e práticas para monitorar e registrar eventos no Kubernetes', 5, 2);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Segurança e Autenticação', 'Práticas de segurança e autenticação no ambiente Kubernetes', 6, 2);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Ferramentas e Ecossistema', 'Exploração de ferramentas e complementos no ecossistema do Kubernetes', 7, 2);

INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Fundamentos de Python', 'Conceitos básicos e estruturas de Python', 1, 3);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Manipulação de Dados', 'Trabalhando com dados e estruturas de dados em Python', 2, 3);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Programação Orientada a Objetos', 'Princípios e práticas de OOP em Python', 3, 3);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Bibliotecas e Frameworks', 'Uso de bibliotecas e frameworks populares em Python', 4, 3);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Aplicações Práticas', 'Aplicações e projetos práticos utilizando Python', 5, 3);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Testes e Depuração', 'Estratégias de teste e depuração de código Python', 6, 3);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Avançado e Tendências', 'Tópicos avançados e tendências na linguagem Python', 7, 3);

INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Introdução ao React', 'Conceitos fundamentais e iniciação ao React', 1, 4);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Componentização', 'Criando e gerenciando componentes no React', 2, 4);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('State e Props', 'Trabalhando com estados e propriedades no React', 3, 4);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Ciclo de Vida', 'Compreensão do ciclo de vida dos componentes no React', 4, 4);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Roteamento', 'Gerenciamento de rotas e navegação no React', 5, 4);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Hooks', 'Utilização e implementação de hooks no React', 6, 4);

INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Introdução ao Machine Learning', 'Visão geral e conceitos fundamentais de ML', 1, 5);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Aprendizado Supervisionado', 'Algoritmos e práticas de aprendizado supervisionado', 2, 5);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Aprendizado Não Supervisionado', 'Algoritmos e práticas de aprendizado não supervisionado', 3, 5);
INSERT INTO tb_modulo (titulo, descricao, ordem, curso_id) VALUES ('Avaliação de Modelos', 'Métricas e estratégias de avaliação de modelos ML', 4, 5);

INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Introdução ao Docker', 'Visão geral e vantagens do Docker', 0, 1, 1, 1, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Comandos Básicos', 'Comandos essenciais para gerenciar containers', 0, 2, 1, 1, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Volumes e Persistência', 'Trabalhando com volumes e persistência de dados', 0, 3, 1, 1, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Redes Docker', 'Gerenciamento e configuração de redes no Docker', 0, 1, 1, 2, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Dockerfile: Construindo Imagens', 'Utilização de Dockerfile para construir imagens', 0, 2, 1, 2, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Publicando Imagens', 'Publicação e compartilhamento de imagens Docker', 0, 3, 1, 2, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Docker Compose: Orquestração de Serviços', 'Utilização do Docker Compose para gerenciar múltiplos containers', 0, 4, 1, 2, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Segurança no Docker', 'Práticas e considerações de segurança no uso do Docker', 0, 1, 1, 3, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Ferramentas Avançadas', 'Exploração de ferramentas e recursos avançados do Docker', 0, 2, 1, 3, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Backup e Recuperação de Dados', 'Estratégias e práticas para backup e recuperação de dados em containers', 0, 4, 1, 3, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Docker Swarm: Orquestração de Clusters', 'Utilização do Docker Swarm para orquestrar clusters de containers', 0, 1, 1, 4, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Casos de Uso Avançados', 'Apresentação de casos de uso avançados e soluções com Docker', 0, 2, 1, 4, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Melhores Práticas com Docker', 'Melhores práticas e dicas para uso eficaz do Docker', 0, 3, 1, 4, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0')
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('CI/CD com Docker', 'Integração e entrega contínua utilizando Docker', 0, 4, 1, 4, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Docker e Kubernetes', 'Integração e uso conjunto de Docker e Kubernetes', 0, 1, 1, 5, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Gestão de Configurações com Docker', 'Gerenciamento e automação de configurações com Docker', 0, 2, 1, 5, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Docker e Segurança Cibernética', 'Considerações e práticas de segurança cibernética com Docker', 0, 3, 1, 5, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Autenticação e Autorização com Docker', 'Práticas e estratégias de autenticação e autorização em containers', 0, 4, 1, 5, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');

INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Introdução ao Kubernetes', 'Visão geral e vantagens do Kubernetes', 0, 1, 2, 8, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Arquitetura do Kubernetes', 'Entendendo a arquitetura do Kubernetes', 0, 2, 2, 8, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Implantação de Clusters', 'Implantação e configuração de clusters Kubernetes', 0, 3, 2, 8, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Kubectl: Ferramenta de Linha de Comando', 'Utilização do Kubectl para interagir com clusters', 0, 4, 2, 8, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Kubernetes Dashboard', 'Configuração e uso do painel de controle Kubernetes', 0, 1, 2, 9, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Implantação e Gerenciamento de Aplicações', 'Implantação e gerenciamento de aplicações no Kubernetes', 0, 2, 2, 9, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Escalonamento e Autoescalonamento', 'Estratégias de escalonamento e autoescalonamento no Kubernetes', 0, 3, 2, 9, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Gerenciamento de Estado', 'Gerenciamento de estado e persistência de dados no Kubernetes', 0, 4, 2, 9, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Monitoramento e Logs', 'Monitoramento e obtenção de logs em clusters Kubernetes', 0, 1, 2, 10, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Atualizações e Rollbacks', 'Estratégias para atualizações e rollbacks de aplicações', 0, 2, 2, 10, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Segurança no Kubernetes', 'Práticas e considerações de segurança em ambientes Kubernetes', 0, 3, 2, 10, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Redes Avançadas no Kubernetes', 'Configurações e práticas avançadas de rede no Kubernetes', 0, 4, 2, 10, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Kubernetes em Ambientes de Produção', 'Desafios e melhores práticas para ambientes de produção', 0, 1, 2, 11, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Casos de Uso Avançados', 'Exploração de casos de uso avançados com Kubernetes', 0, 2, 2, 11, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Kubernetes em Ambientes de Desenvolvimento', 'Uso e otimizações para ambientes de desenvolvimento', 0, 3, 2, 11, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Melhores Práticas com Kubernetes', 'Dicas e práticas recomendadas para uso eficaz do Kubernetes', 0, 4, 2, 11, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');

INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Introdução ao Python', 'Visão geral e conceitos básicos de Python', 0, 1, 3, 15, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Estruturas de Dados em Python', 'Exploração de estruturas de dados em Python', 0, 2, 3, 15, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Funções e Módulos', 'Uso de funções e módulos em Python', 0, 3, 3, 15, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Trabalhando com Strings', 'Manipulação e métodos para strings em Python', 0, 4, 3, 16, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Estruturas de Controle em Python', 'Uso de estruturas de controle em Python', 0, 5, 3, 16, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Tratamento de Exceções', 'Manejo e tratamento de exceções em Python', 0, 6, 3, 16, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Iteradores e Geradores', 'Utilização de iteradores e geradores em Python', 0, 7, 3, 16, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Arquivos em Python', 'Manipulação e operações com arquivos em Python', 0, 8, 3, 17, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Programação Orientada a Objetos', 'Conceitos e práticas de POO em Python', 0, 9, 3, 17, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Testes em Python', 'Estratégias e ferramentas para testes em Python', 0, 10, 3, 17, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Frameworks e Bibliotecas', 'Exploração de frameworks e bibliotecas Python', 0, 11, 3, 17, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Projetos e Aplicações', 'Desenvolvimento de projetos e aplicações em Python', 0, 12, 3, 18, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');

INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Introdução ao React', 'Visão geral e princípios básicos do React', 0, 1, 4, 22, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Componentes em React', 'Uso e criação de componentes em React', 0, 2, 4, 22, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('State e Props', 'Gerenciamento de estado e propriedades em React', 0, 3, 4, 22, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Hooks em React', 'Utilização de hooks para estados em React', 0, 4, 4, 22, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('React Router', 'Navegação e gerenciamento de rotas em React', 0, 5, 4, 23, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Context API', 'Utilização da Context API em aplicações React', 0, 6, 4, 23, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Redux em React', 'Gerenciamento de estado com Redux em React', 0, 7, 4, 24, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Estilização em React', 'Técnicas e ferramentas para estilização em React', 0, 8, 4, 24, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Testes em React', 'Estratégias e ferramentas para testes em React', 0, 9, 4, 24, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Desempenho em React', 'Melhoria de desempenho e otimização em React', 0, 10, 4, 25, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Aplicações Práticas em React', 'Desenvolvimento de aplicações completas em React', 0, 11, 4, 25, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');

INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Introdução ao Machine Learning', 'Visão geral e fundamentos do Machine Learning', 0, 1, 5, 29, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Aprendizado Supervisionado', 'Conceitos e algoritmos de aprendizado supervisionado', 0, 2, 5, 29, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Aprendizado Não Supervisionado', 'Conceitos e algoritmos de aprendizado não supervisionado', 0, 3, 5, 29, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Aprendizado por Reforço', 'Conceitos e algoritmos de aprendizado por reforço', 0, 4, 5, 30, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Redes Neurais', 'Fundamentos e aplicações de redes neurais', 0, 5, 5, 30, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');
INSERT INTO tb_aula (titulo, descricao, assistida, ordem, curso_id, modulo_id, video) VALUES ('Processamento de Linguagem Natural', 'Aplicações de Machine Learning em NLP', 0, 6, 5, 30, 'https://www.youtube.com/embed/xKJmEC5ieOk?rel=0&amp;showinfo=0');


