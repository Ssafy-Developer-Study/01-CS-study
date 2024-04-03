# A U T H E N T I C A T I O N

# 𝒜𝓊𝓉𝒽𝑒𝓃𝓉𝒾𝒸𝒶𝓉𝒾𝑜𝓃

## 출처

### [Auth - simulacre(Velog)](https://velog.io/@parkirae/series/Auth)

### [Servlet Authentication Filter](https://www.javatpoint.com/authentication-filter)

### [Spring Security (Velog)](https://velog.io/@dh1010a/Spring-Spring-Security를-이용한-로그인-구현-스프링부트-3.X-버전-1)

# 1. 인증의 기본 개념 이해하기

## Basic auth

> 상태가 없는(Stateless) 웹 애플리케이션에서 인증을 구현할 수 있는 방법은 무엇일까요? 가장 단순한 방법은 모든 HTTP Request에 ID와 PW를 같이 보내는 것입니다.
> 
- Basic Auth는 최초 로그인 후, `request header`에 Base64 인코딩을 해서 문자열을 보낸다
    - ID와 PW를 column`:`으로 이어붙이고 Base64로 인코딩 한 후 Basic 문자열을 붙여서 내보냄

```
Authorization: Basic dlrjsdPtldlqslek==

```

서버의 동작

1. 해당 요청을 수신하면 Base64로 인코딩된 문자열을 디코딩
2. DB의 레코드와 비교
3. 일치하면 요청 수행, 아니면 거부.

### 단점

1. MITM(Manipulator in the Middle Attack)에 취약: `ID와 PW가 탈취됨`
- HTTP에서 사용 불가, HTTPS에서만 사용 가능
1. 로그아웃 기능 제공 불가
- ID와 PW를 들고 있는 세션은 항상 로그인 됨
1. DB 부담 증가
- 매번 비교하기 때문에 과부화 확률이 올라감
1. 인증 서버가 단일 장애점 (Single Point of Failure)이 됨
- 이거 하나만 망가져도 전체 시스템이 작동 불가가 됨

## Bearer auth

> 최초 로그인 시 서버가 만들어 줍니다. 서버가 토큰을 만들어 클라이언트에게 반환하면 클라이언트는 이후 요청에 아이디와 비밀번호 대신 토큰을 넘겨 자신의 인증 여부를 증명합니다. 토큰 기반 인증은 Request Header에 <TOKEN>을 명시합니다. Bearer Token은 아래처럼 생겼습니다.
> 

```
Authorization: Bearer qpdjfjxhRmsRKwldhkTekdlwprhewpdltmsxhzmsdmfgkftndlTek

```

### 장점

1. Basic Auth에 비해 안정성 ↑
Basic Auth는 ID와 PW를 모든 요청 헤더에 넣어 전달합니다. 물론 Bearer Auth도 모든 요청 헤더에 넣어 전달하는데 보안 측면에서 조금 더 안전합니다.
2. 유효기간 관리
Basic Auth는 유효기간을 정할 수 없지만, Bearer Auth는 관리 가능. -> `모든 디바이스 로그아웃` 기능 구현 가능

### 단점

1. DB 과부화 이슈 여전히 남아있음
- 여전히 DB에서 대조한다
1. 인증 서버가 단일 장애점 (Single Point of Failure)이 됨
- 여전히 해결되지 않았다

## JWT `Json Web Token`

- 스케일 문제를 해결할 수 어렵다는 단점 해결

```
Authorization: Bearer asdfgqwerty. qpdjfjxhRmsRKwldhkTekdlwprhewpdltmsxhzmsdmfgkftndlTek.qqqqqqqqqqqqqqqq.

```

- JWT는 **전자 서명 (Digital Signature)된** 토큰
    - JSON 형태로 이뤄져 있음
    - {header}, {payload}, {signature}로 구성
- 전자 서명이 뭔데?
    - 서명하고 싶은 메시지를 해싱하고 개인키로 암호화 했을 때 나오는 값

## JWT디코딩

```jsx
{ // {header}
  "typ": "JWT",
  "alg": "HS512"
},
{ // {payload}
  "sub": "546546574869465465",
  "iss": "demo app",
  "iat": "54564654",
  "exp": "54564654"
}.
qqqqqqqqqqqqqqqq
// {signature}

```

- header - `typ: 토큰 타입(JWT)`, `alg: 해싱 알고리즘`
- Payload - `sub - subject 토큰의 주인`, `iss - issuer 발행 주체`, `iat - issued at 발행 시간`, `exp - expire 만료 시간`
- signature - 유효성 검사에 사용

### JWT 인증 방식

![image](https://gist.github.com/assets/39848764/dc32b1ec-01e2-46cd-bc05-2c71033e568e)

1. 클라이언트 - 로그인 요청
2. 서버 - 유저 정보 받아서 `header`, `payload` 작성
3. 서버 - `2`의 값을 secret key로 전자 서명
4. 서버 - header, payload를 base64로 인코딩, 클라이언트에 반환
5. 클라이언트 - 재요청 (`4`에서 받은 토큰 들고있음)
6. 서버 - 디코딩한 JSON을 `header, payload, signature`로 나눔
7. 서버 - header, payload, secret key로 전자서명 생성
8. 서버 - 방금 만든 서명과 signatrue를 비교해 유효성 검사
9. {signature}가 일치한다면 위조된 토큰이 아님 -> 유효성 검사 끝냄

## OAuth 2.0

### 용어 정리

- 서드파티 / 클라이언트
    - 소셜 로그인을 사용하려는 애플리케이션 (ex. 내가 만든 사이트라던가)
- 리소스
    - 서드파티 애플리케이션이 접근하려고 하는 자원 (구글 계정? or auth service)
- 리소스 오너
    - 리소스를 가지고 있는 것 (사용자)
- 리소스 서버
    - 리소스를 가지는 서버
- 인가 서버
    - 인가만 담당하는 서버 (인가 서버와 리소스 서버는 다를수도, 같을 수도 있음)
- 제한된 액세스
    - 서드파티 애플리케이션이 가지는 `리소스 오너가 허락한 리소스에만 접근할 수 있도록 하겠다`는 뜻의 속성

### 플로우

![image](https://gist.github.com/assets/39848764/acb58444-6a69-460d-aa36-984593199b71)

1. 사용자가 소셜 로그인 하기 요청을 보냅니다.
2. 백엔드 서버가 요청을 받습니다.
3. 백엔드 서버는 해당 요청을 인가 페이지로 리다이렉트합니다.
4. 사용자가 인가 페이지를 보게 됩니다.
5. 사용자가 소셜 로그인을 요청을 보냅니다.
6. 사용자가 인가 서버에 로그인됩니다.
7. 인가 서버가 접근 권한을 부여하는 페이지로 리다이렉트합니다.
8. 사용자가 접근 인가 버튼을 클릭합니다.
9. 인가 서버가 사용자를 서드 파티 애플리케이션(콜백 URL)으로 리다이렉트합니다.
10. 서드 파티 애플리케이션은 인가 서버를 받은 토큰을 사용자에게 전달합니다.
11. 토큰을 받은 사용자는 토큰을 이용해 사용자의 정보에 접근합니다.

> 서드 파티 애플리케이션은 인가 페이지와 서드 파티 애플리케이션 페이지(콜백 URL)를 인가 서버에 알려주어야 합니다.
> 

# 2. 서블릿에서의 적용 이해하기

> "서블릿에서 어떻게 해야 하는지 알아야 스프링에서 감동이 생긴다" - 걍 내 생각
> 

### [Basic auth]

```java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.StringTokenizer;

@WebServlet("/protected/*")
public class BasicAuthServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract Authorization header
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Basic")) {
            // Extract the encoded username and password
            String encodedCredentials = authHeader.substring("Basic".length()).trim();
            String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
            
            // Split username and password tokens
            StringTokenizer tokenizer = new StringTokenizer(decodedCredentials, ":");
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            
            // Validate credentials
            if (validateCredentials(username, password)) {
                // Proceed with the request processing
                response.getWriter().println("Authenticated Successfully");
            } else {
                // Reject the request
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Authentication Failed: Invalid username or password");
            }
        } else {
            // Request lacks authentication information
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"Access to the protected resource\"");
            response.getWriter().println("Authentication Required");
        }
    }
		private boolean validateCredentials(String username, String password) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		
		    try {
		        // Change these variables to match your database connection
		        String jdbcURL = "jdbc:mysql://localhost:3306/yourDatabaseName";
		        String dbUser = "yourDatabaseUsername";
		        String dbPassword = "yourDatabasePassword";
		
		        // Establish the connection to the database
		        connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		
		        // Prepare a statement to query the database
		        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		        preparedStatement = connection.prepareStatement(sql);
		        preparedStatement.setString(1, username);
		        preparedStatement.setString(2, password);
		
		        // Execute the query
		        resultSet = preparedStatement.executeQuery();
		
		        // Check if any record matches the provided credentials
		        return resultSet.next();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    } finally {
		        // Clean up database resources
		        try {
		            if (resultSet != null) resultSet.close();
		            if (preparedStatement != null) preparedStatement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
}
```

### [Bearer Auth]

```java
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/protected/*")
public class BearerAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization logic can go here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Retrieve the Authorization header
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extract the token from the Authorization header
            String token = authHeader.substring(7);

            // Validate the extracted token
            if (validateToken(token)) {
                // Token is valid; continue the request
                chain.doFilter(request, response);
            } else {
                // Token is invalid; reject the request
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            }
        } else {
            // No Authorization header present; reject the request
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
        }
    }

    private boolean validateToken(String token) {
        // Implement token validation logic here
        // This is a placeholder logic; replace it with your actual token validation
        return "secretToken".equals(token);
    }

    @Override
    public void destroy() {
        // Cleanup code can go here
    }
}

```

### [JWT Auth]

```java
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/secured/*")
public class JwtRequestFilter implements Filter {

    private String secretKey = "yourSecretKey"; // Use a strong, unique key

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestTokenHeader = httpRequest.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7); // Remove Bearer prefix
            try {
                // Validate the token
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey.getBytes())
                        .parseClaimsJws(jwtToken)
                        .getBody();

                // If needed, use claims to authorize user actions
                
                chain.doFilter(request, response);
            } catch (SignatureException e) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Signature");
            } catch (Exception e) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            }
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token does not begin with Bearer String or is missing");
        }
    }

    @Override
    public void destroy() {
    }
}

```

### [OAuth2.0 auth]

```java
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/callback")
public class OAuth2CallbackServlet extends HttpServlet {

    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        
        if (code != null) {
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    "https://oauth2.googleapis.com/token",
                    CLIENT_ID,
                    CLIENT_SECRET,
                    code,
                    "http://localhost:8080/callback")  // Specify the same redirect URI that you use with your web app
                    .execute();
            
            // Use tokenResponse.getAccessToken() to make authenticated requests.
            
            // Redirect or respond based on successful authentication
            response.sendRedirect("/success");
        } else {
            response.sendRedirect("/error"); // Handle the error scenario
        }
    }
}

```

# 3. 스프링에서는 어떻게 사용하나

> 지금 당장 스프링 공부할 건 아니니까 대충 느낌만 보자구요?
> 

### [Basic auth]

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll() // Allow access to certain endpoints
                .anyRequest().authenticated() // All other paths must be authenticated
                .and()
            .httpBasic(); // Use Basic Authentication
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user =
                User.withUsername("user")
                    .password(encoder.encode("password"))
                    .roles("USER")
                    .build();

        return new InMemoryUserDetailsManager(user);
    }
}

```

### [Bearer auth]

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
                .antMatchers("/","/api/auth/**").permitAll()
                .anyRequest().authenticated();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```

### [JWT auth]

```java
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (jwt != null && !jwt.isEmpty()) {
                String username = Jwts.parser().setSigningKey(jwtSecret.getBytes())
                        .parseClaimsJws(jwt)
                        .getBody()
                        .getSubject();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Could not set user authentication in security context: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

```

### [Oauth2.0 auth]

```java
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttribute("name");
    }
}
```

# 기타: OAuth2.0으로 구글 로그인 → 회원가입 구현하기

## **Step 2: Extend the Security Configuration**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private ApplicationUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        
        ApplicationUser applicationUser = userRepository.findByEmail(email);
        if (applicationUser == null) {
            // User not found. Redirect to registration page.
            // Note: You'd typically add a redirect logic or a flag to indicate a new user registration is needed.
        }
        // For simplicity, directly return the user details. Adjust based on your registration flow.
        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), 
            oAuth2User.getAttributes(), 
            "email");
    }
}

```

## **Step 3: Configure the Custom OAuth2UserService**
