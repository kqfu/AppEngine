<!DOCTYPE html>
<!-- [START_EXCLUDE] -->
<%--
  ~ Copyright 2017 Google Inc.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License"); you
  ~ may not use this file except in compliance with the License. You may
  ~ obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
  ~ implied. See the License for the specific language governing
  ~ permissions and limitations under the License.
  --%>
<!-- [END_EXCLUDE] -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.appengine.java8.Mortgage" %>
<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
  <title>Mortgage Calculator</title>
</head>
<body>
    <h1>Mortgage Calculator</h1>
  
  <form action="/mortgage">
    Mortgage amount:<br>
    <input type="text" name="amount" value=""> <br>
    Total Fees:<br>
    <input type="text" name="fees" value=""> <br>
    Interest:<br>
    <input type="text" name="interest" value=""> <br>
    Years:<br>
    <input type="text" name="years" value=""> <br>
    <br> <br>
    <input type="submit" value="Submit">
  </form>

</body>
</html>
