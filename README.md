# Log Access Analytics(LAA)

### Requires

* Java 11+
* Maven 3.3+
* Elasticsearch 7.5.1
The application is using an Elasticsearch Cloud container, already configured. To change the settings you will need to adjust the ElasticsearchConstants.java file

### Run

Maven:
<pre><code>mvn clean install</code></pre>
<pre><code>mvn jetty:run</code></pre>

### Especification


### References

<https://www.eclipse.org/jetty/documentation/9.4.x/jetty-maven-plugin.html>
<https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html>