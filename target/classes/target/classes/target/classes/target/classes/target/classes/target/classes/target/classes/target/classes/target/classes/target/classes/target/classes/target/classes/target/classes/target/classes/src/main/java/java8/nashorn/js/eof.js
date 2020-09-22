//jjs -scripting src/main/java/java8/nashorn/js/eof.js
//In Nashorn, heredocs are simply multi-line strings.
// You can create it with << followed by a special termination marker, which is EOF. You can also embed JavaScript expressions in ${...} expressions.
var message = <<EOF
This is a java script file
it contains multiple lines
of code.
    lets execute.
    EOF
print(message);