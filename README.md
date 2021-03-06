[![Build Status](https://api.travis-ci.org/webfirmframework/wff.svg?branch=master)](https://travis-ci.org/webfirmframework/wff)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/410601e16dc54b0a973c03845ad790c2)](https://www.codacy.com/app/webfirm-framework/wff?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=webfirmframework/wff&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.webfirmframework/wffweb/badge.svg)](http://search.maven.org/#artifactdetails%7Ccom.webfirmframework%7Cwffweb%7C2.0.3%7Cjar)

# wffweb
wffweb is one of the modules of webfirmframework. It's an open source java framework for realtime application development which can generate html5 and css3 from java code, [read more...](https://webfirmframework.github.io/)


#### [check out wffweb-2.x.x main features](https://www.youtube.com/watch?v=UWoNliHOy6A)
##### [check out wffweb-2.x.x sample projects](https://github.com/webfirmframework/tomcat-8-wffweb-demo-apps)


##### check out this demo app deployed at [https://wffweb.herokuapp.com](https://wffweb.herokuapp.com)

Here are some sample codes

##### Sample1 :-
~~~
Html html = new Html(null) {
       	 
        	Head head = new Head(this);
       	 
        	Body body = new Body(this) {
           	 
            	Blank blank = new Blank(this, "Hello World");
           	 
        	};
       	 
};
// prepends the doc type <!DOCTYPE html>
html.setPrependDocType(true);
System.out.println(html.toHtmlString()); 
~~~

or the same in another coding style
~~~
Html html = new Html(null) {
       {	 
              new Head(this);
       
              new Body(this) {
                     { 
                            new NoTag(this, "Hello World");
                     }
              };
       } 
};
// prepends the doc type <!DOCTYPE html>
html.setPrependDocType(true);
System.out.println(html.toHtmlString()); 
~~~

prints the following output
~~~
<!DOCTYPE html>
<html>
<head>
</head>
<body>
Hello World
</body>
</html>
~~~

##### Sample2 :-
~~~
Div div = new Div(null); 
~~~
or 
~~~
Div div = new Div(null) {
};
~~~
prints :- 
~~~
<div></div>
~~~

##### Sample3 :-
~~~
Div div = new Div(null) {
       	 
        	Div div1 = new Div(this);  
       	 
        	Div div2 = new Div(this);
       	 
};
~~~
prints :- 
~~~
<div>
<div>
</div>
<div>
</div>
</div>
~~~
##### Sample4 :-
~~~
Div div = new Div(null, new Width(50, CssLengthUnit.PX));
~~~
prints :- 
~~~
<div width="50px"></div>
~~~

##### Sample5 :-
~~~
Div div = new Div(null, new Style(new BackgroundColor("green")));
~~~
prints :- 
~~~
<div style="background-color: green;"></div>
~~~

##### Sample6 :-
```
final Style paragraphStyle = new Style("color:red");

Html html = new Html(null, new CustomAttribute("some", "val"), new Id("htmlId"),
		new Style("background:white;width:15px")) {{

	new Div(this, new Id("outerDivId")) {

		int paragraphCount = 0;

		Div contentDiv = new Div(this) {
			{

				new H1(this) {
					NoTag headerContent = new NoTag(this, "Web Firm Framework");
				};

				for (paragraphCount = 1; paragraphCount < 4; paragraphCount++) {
					new P(this, paragraphStyle) {
						Blank paragraphContent = new Blank(this,
								"Web Firm Framework Paragraph " + paragraphCount);
					};
				}

			}
		};
	};

	new Div(this, new Hidden());
}};

paragraphStyle.addCssProperty(AlignContent.CENTER);

System.out.println(html.toHtmlString());
```
prints

```
<html some="val" id="htmlId" style="background:white;width:15px;">
<div id="outerDivId">
    <div>
        <h1>Web Firm Framework</h1>
        <p style="color:red;align-content:center;">Web Firm Framework Paragraph 1</p>
        <p style="color:red;align-content:center;">Web Firm Framework Paragraph 2</p>
        <p style="color:red;align-content:center;">Web Firm Framework Paragraph 3</p>
    </div>
</div>
<div hidden></div>
</html>
```
and we can add/change styles later, eg:-
```
paragraphStyle.addCssProperties(new WidthCss(100, CssLengthUnit.PER));

Color color = (Color) paragraphStyle
        .getCssProperty(CssNameConstants.COLOR);
        
color.setCssValue(CssColorName.BROWN.getColorName());

System.out.println(html.toHtmlString());

```
It will add width 100% in aboutParagraph and will change color to brown, its generated html code will be as follows

```
<html some="val" id="htmlId" style="background:white;width:15px;">
<div id="outerDivId">
    <div>
        <h1>Web Firm Framework</h1>
        <p style="color:brown;align-content:center;width:100.0%;">Web Firm Framework Paragraph 1</p>
        <p style="color:brown;align-content:center;width:100.0%;">Web Firm Framework Paragraph 2</p>
        <p style="color:brown;align-content:center;width:100.0%;">Web Firm Framework Paragraph 3</p>
    </div>
</div>
<div hidden></div>
</html>
```


##### Checkout 

[Refer Developers Guide to get started](https://webfirmframework.github.io/developers-guide/get-started.html)

[How to resolve wffweb dependency in build tools like maven, ivy, Scala SBT, Leiningen, Grape, Gradle Grails or Apache Buildr](https://webfirmframework.github.io/developers-guide/how-to-resolve-dependency-in-build-tools.html)

[wffweb released versions](https://webfirmframework.github.io/developers-guide/wffweb-released-versions.html)

[You can request features or report bugs here](https://github.com/webfirmframework/wff/issues)

Feel free to write us @ admin@webfirmframework.com for any assistance.
