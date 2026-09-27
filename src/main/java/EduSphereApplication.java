import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class EduSphereApplication {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        server.createContext("/", EduSphereApplication::handleRequest);

        server.setExecutor(null);

        server.start();

        System.out.println("EduSphere Learning Academy started!");
        System.out.println("Server running on port 8080");
    }

    private static void handleRequest(HttpExchange exchange)
            throws IOException {

        String path = exchange.getRequestURI().getPath();

        String html;

        switch (path) {

            case "/courses":
                html = coursesPage();
                break;

            case "/students":
                html = studentsPage();
                break;

            case "/about":
                html = aboutPage();
                break;

            case "/contact":
                html = contactPage();
                break;

            default:
                html = homePage();
        }

        byte[] response = html.getBytes("UTF-8");

        exchange.getResponseHeaders()
                .set("Content-Type", "text/html; charset=UTF-8");

        exchange.sendResponseHeaders(200, response.length);

        try (OutputStream outputStream =
                     exchange.getResponseBody()) {

            outputStream.write(response);
        }
    }

    private static String header(String activePage) {

        String homeClass =
                activePage.equals("home")
                        ? "class=\"active\"" : "";

        String coursesClass =
                activePage.equals("courses")
                        ? "class=\"active\"" : "";

        String studentsClass =
                activePage.equals("students")
                        ? "class=\"active\"" : "";

        String aboutClass =
                activePage.equals("about")
                        ? "class=\"active\"" : "";

        String contactClass =
                activePage.equals("contact")
                        ? "class=\"active\"" : "";

        return """
                <!DOCTYPE html>
                <html>
                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">

                    <title>EduSphere Learning Academy</title>

                    <style>

                        * {
                            box-sizing: border-box;
                            margin: 0;
                            padding: 0;
                        }

                        body {
                            font-family: Arial, sans-serif;
                            background: #f5f7ff;
                            color: #222;
                        }

                        nav {
                            background: linear-gradient(
                                90deg,
                                #4f46e5,
                                #7c3aed,
                                #db2777
                            );

                            padding: 18px 8%%;

                            display: flex;

                            justify-content: space-between;

                            align-items: center;

                            flex-wrap: wrap;
                        }

                        .logo {
                            color: white;
                            font-size: 25px;
                            font-weight: bold;
                        }

                        nav a {
                            color: white;
                            text-decoration: none;

                            margin: 5px;

                            padding: 10px 16px;

                            border-radius: 20px;

                            font-weight: bold;

                            display: inline-block;
                        }

                        nav a:hover {
                            background: rgba(
                                255,
                                255,
                                255,
                                0.2
                            );
                        }

                        .active {
                            background: white;
                            color: #5b21b6 !important;
                        }

                        .hero {
                            min-height: 480px;

                            display: flex;

                            justify-content: center;

                            align-items: center;

                            text-align: center;

                            padding: 60px 20px;

                            background: linear-gradient(
                                135deg,
                                #eef2ff,
                                #fce7f3
                            );
                        }

                        .hero-content {
                            max-width: 850px;
                        }

                        .hero h1 {
                            font-size: 55px;
                            color: #4f46e5;
                            margin-bottom: 20px;
                        }

                        .hero p {
                            font-size: 21px;
                            line-height: 1.7;
                            color: #555;
                            margin-bottom: 30px;
                        }

                        .button {
                            display: inline-block;

                            background: linear-gradient(
                                90deg,
                                #4f46e5,
                                #db2777
                            );

                            color: white;

                            text-decoration: none;

                            padding: 14px 28px;

                            border-radius: 30px;

                            font-weight: bold;

                            margin: 5px;
                        }

                        .button:hover {
                            transform: translateY(-2px);
                        }

                        .container {
                            max-width: 1100px;

                            margin: auto;

                            padding: 60px 20px;
                        }

                        .title {
                            text-align: center;

                            color: #4f46e5;

                            font-size: 38px;

                            margin-bottom: 40px;
                        }

                        .cards {
                            display: grid;

                            grid-template-columns:
                                repeat(
                                    auto-fit,
                                    minmax(240px, 1fr)
                                );

                            gap: 25px;
                        }

                        .card {
                            background: white;

                            padding: 30px;

                            border-radius: 18px;

                            box-shadow:
                                0 8px 25px
                                rgba(0,0,0,0.08);

                            text-align: center;
                        }

                        .card h3 {
                            color: #7c3aed;

                            margin-bottom: 15px;
                        }

                        .card p {
                            color: #666;

                            line-height: 1.6;

                            margin-bottom: 20px;
                        }

                        .icon {
                            font-size: 45px;

                            margin-bottom: 15px;
                        }

                        .stats {
                            display: grid;

                            grid-template-columns:
                                repeat(
                                    auto-fit,
                                    minmax(180px, 1fr)
                                );

                            gap: 20px;

                            margin-top: 50px;
                        }

                        .stat {
                            background: white;

                            padding: 30px;

                            border-radius: 18px;

                            text-align: center;
                        }

                        .stat h2 {
                            color: #db2777;

                            font-size: 35px;

                            margin-bottom: 10px;
                        }

                        .student {
                            background: white;

                            padding: 25px;

                            border-radius: 15px;

                            margin-bottom: 20px;

                            box-shadow:
                                0 5px 20px
                                rgba(0,0,0,0.07);
                        }

                        .progress {
                            height: 12px;

                            background: #e5e7eb;

                            border-radius: 10px;

                            overflow: hidden;

                            margin-top: 10px;
                        }

                        .progress-bar {
                            height: 100%%;

                            background: linear-gradient(
                                90deg,
                                #4f46e5,
                                #db2777
                            );
                        }

                        .contact-box {
                            background: white;

                            max-width: 700px;

                            margin: auto;

                            padding: 35px;

                            border-radius: 20px;

                            box-shadow:
                                0 8px 25px
                                rgba(0,0,0,0.08);
                        }

                        input,
                        textarea {
                            width: 100%%;

                            padding: 14px;

                            margin-bottom: 15px;

                            border: 1px solid #ddd;

                            border-radius: 10px;

                            font-size: 16px;
                        }

                        textarea {
                            height: 130px;

                            resize: vertical;
                        }

                        footer {
                            background: #111827;

                            color: white;

                            text-align: center;

                            padding: 30px;

                            margin-top: 40px;
                        }

                    </style>

                </head>

                <body>

                    <nav>

                        <div class="logo">
                            🎓 EduSphere
                        </div>

                        <div>

                            <a href="/" %s>Home</a>

                            <a href="/courses" %s>Courses</a>

                            <a href="/students" %s>Students</a>

                            <a href="/about" %s>About</a>

                            <a href="/contact" %s>Contact</a>

                        </div>

                    </nav>
                """.formatted(
                homeClass,
                coursesClass,
                studentsClass,
                aboutClass,
                contactClass
        );
    }

    private static String footer() {

        return """
                <footer>

                    <h3>🎓 EduSphere Learning Academy</h3>

                    <p>
                        Learn today. Build tomorrow.
                    </p>

                    <p>
                        © 2026 EduSphere Learning Academy
                    </p>

                </footer>

                </body>

                </html>
                """;
    }

    private static String homePage() {

        return header("home") + """

                <section class="hero">

                    <div class="hero-content">

                        <h1>
                            Learn. Build. Grow.
                        </h1>

                        <p>
                            Welcome to EduSphere Learning Academy.
                            Build practical technology skills
                            and prepare for your IT career.
                        </p>

                        <a class="button"
                           href="/courses">
                            Explore Courses
                        </a>

                        <a class="button"
                           href="/contact">
                            Contact Us
                        </a>

                    </div>

                </section>


                <div class="container">

                    <h2 class="title">
                        Popular Learning Paths
                    </h2>

                    <div class="cards">

                        <div class="card">

                            <div class="icon">☁️</div>

                            <h3>AWS Cloud</h3>

                            <p>
                                Learn cloud infrastructure,
                                EC2, networking and deployment.
                            </p>

                        </div>


                        <div class="card">

                            <div class="icon">🐳</div>

                            <h3>Docker</h3>

                            <p>
                                Learn containers and
                                application deployment.
                            </p>

                        </div>


                        <div class="card">

                            <div class="icon">⚙️</div>

                            <h3>DevOps</h3>

                            <p>
                                Learn CI/CD, Jenkins,
                                Git and automation.
                            </p>

                        </div>

                    </div>


                    <div class="stats">

                        <div class="stat">

                            <h2>500+</h2>

                            <p>Students</p>

                        </div>


                        <div class="stat">

                            <h2>20+</h2>

                            <p>Courses</p>

                        </div>


                        <div class="stat">

                            <h2>95%</h2>

                            <p>Completion Rate</p>

                        </div>


                        <div class="stat">

                            <h2>24/7</h2>

                            <p>Learning Access</p>

                        </div>

                    </div>

                </div>

                """ + footer();
    }

    private static String coursesPage() {

        return header("courses") + """

                <div class="container">

                    <h1 class="title">
                        Our Courses
                    </h1>

                    <div class="cards">

                        <div class="card">

                            <div class="icon">🐧</div>

                            <h3>Linux</h3>

                            <p>
                                Learn Linux commands,
                                users, permissions and
                                server administration.
                            </p>

                            <a class="button"
                               href="/contact">
                                Enroll
                            </a>

                        </div>


                        <div class="card">

                            <div class="icon">🌿</div>

                            <h3>Git & GitHub</h3>

                            <p>
                                Learn version control,
                                branches, commits and
                                collaboration.
                            </p>

                            <a class="button"
                               href="/contact">
                                Enroll
                            </a>

                        </div>


                        <div class="card">

                            <div class="icon">🔨</div>

                            <h3>Maven</h3>

                            <p>
                                Learn Java build
                                automation and packages.
                            </p>

                            <a class="button"
                               href="/contact">
                                Enroll
                            </a>

                        </div>


                        <div class="card">

                            <div class="icon">🔄</div>

                            <h3>Jenkins</h3>

                            <p>
                                Build CI/CD pipelines
                                and automate deployments.
                            </p>

                            <a class="button"
                               href="/contact">
                                Enroll
                            </a>

                        </div>


                        <div class="card">

                            <div class="icon">🐳</div>

                            <h3>Docker</h3>

                            <p>
                                Build, run and manage
                                application containers.
                            </p>

                            <a class="button"
                               href="/contact">
                                Enroll
                            </a>

                        </div>


                        <div class="card">

                            <div class="icon">☸️</div>

                            <h3>Kubernetes</h3>

                            <p>
                                Learn container
                                orchestration and deployment.
                            </p>

                            <a class="button"
                               href="/contact">
                                Enroll
                            </a>

                        </div>

                    </div>

                </div>

                """ + footer();
    }

    private static String studentsPage() {

        return header("students") + """

                <div class="container">

                    <h1 class="title">
                        Student Progress
                    </h1>


                    <div class="student">

                        <h3>Arun Kumar</h3>

                        <p>
                            DevOps Fundamentals
                        </p>

                        <div class="progress">

                            <div class="progress-bar"
                                 style="width: 85%;">
                            </div>

                        </div>

                        <p>85% Completed</p>

                    </div>


                    <div class="student">

                        <h3>Priya Sharma</h3>

                        <p>
                            AWS Cloud
                        </p>

                        <div class="progress">

                            <div class="progress-bar"
                                 style="width: 70%;">
                            </div>

                        </div>

                        <p>70% Completed</p>

                    </div>


                    <div class="student">

                        <h3>Rahul Raj</h3>

                        <p>
                            Docker & Kubernetes
                        </p>

                        <div class="progress">

                            <div class="progress-bar"
                                 style="width: 60%;">
                            </div>

                        </div>

                        <p>60% Completed</p>

                    </div>

                </div>

                """ + footer();
    }

    private static String aboutPage() {

        return header("about") + """

                <div class="container">

                    <h1 class="title">
                        About EduSphere
                    </h1>


                    <div class="cards">

                        <div class="card">

                            <div class="icon">🎯</div>

                            <h3>Our Mission</h3>

                            <p>
                                Help students develop
                                practical technology
                                skills through hands-on
                                learning.
                            </p>

                        </div>


                        <div class="card">

                            <div class="icon">🚀</div>

                            <h3>Our Vision</h3>

                            <p>
                                Create job-ready
                                technology professionals
                                through practical
                                education.
                            </p>

                        </div>


                        <div class="card">

                            <div class="icon">🤝</div>

                            <h3>Our Community</h3>

                            <p>
                                Build a community where
                                learners can share,
                                practice and grow together.
                            </p>

                        </div>

                    </div>

                </div>

                """ + footer();
    }

    private static String contactPage() {

        return header("contact") + """

                <div class="container">

                    <h1 class="title">
                        Contact Us
                    </h1>


                    <div class="contact-box">

                        <h2>
                            Get in touch
                        </h2>

                        <br>

                        <p>
                            📧 Email:
                            info@edusphere.example
                        </p>

                        <br>

                        <p>
                            📞 Phone:
                            +91 98765 43210
                        </p>

                        <br>

                        <form>

                            <input
                                type="text"
                                placeholder="Your Name">

                            <input
                                type="email"
                                placeholder="Your Email">

                            <textarea
                                placeholder="Your Message">
                            </textarea>

                            <button
                                class="button"
                                type="submit">
                                Send Message
                            </button>

                        </form>

                    </div>

                </div>

                """ + footer();
    }
}
