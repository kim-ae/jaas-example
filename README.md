# jaas-example
Doing a simple example of authentication using jax + jaas with forms.
#Configuration
In order to work we need to tell the server where is our jass.config.
I only tested it on Tomee Server, so the following steps will work for sure in tomee server I'm not sure if it's the same process to get it working on other servers.
## Eclipse
1. Double-click the Tomcat instance on servers tab
2. Click "Open launch configuration"
3. In the "Arguments" tab, there is a VM arguments input text.
4. Append your parameter: -Djava.security.auth.login.config="", i.e:
    -Djava.security.auth.login.config="<PATH>/jaas.config"

5. Click apply, ok and restart server
## Using TomEE pure
1. Copy the following line to your `cataline.sh`
```bash
JAVA_OPTS="$JAVA_OPTS -Djava.security.auth.login.config=\"<PATH>/jaas.config\""
```

# Source
Basic JAAS: http://www.byteslounge.com/tutorials/jaas-authentication-in-tomcat-example

JAAS with form: http://www.byteslounge.com/tutorials/jaas-form-based-authentication-in-tomcat-example

JAAS logout: http://www.byteslounge.com/tutorials/jaas-logout-example
