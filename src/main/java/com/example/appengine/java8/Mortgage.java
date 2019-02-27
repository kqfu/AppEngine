/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.java8;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(name = "Mortgage", value = "/mortgage")
public class Mortgage extends HttpServlet {
  private static final Logger log = Logger.getLogger(Mortgage.class.getName());

  static double payment(double rate, int periods, double presentValue) {
    // v = 1 + r
    // pay * (1/v + 1/v^2 + 1/v^3 + ... + 1/v^term) = pv
    // pay * 1/v * (1-1/v^term) / (1-1/v) = pv
    // pay - pv * v * (1-1/v) / (1-1/v^term)
    return presentValue * rate / (1 - 1 / Math.pow(1 + rate, periods));
  }

  static double rate(double payment, int periods, double presentValue) {
    double l = .000001, r = 1;
    while (r - l > 0.000001) {
      double mid = (l + r) / 2;
      double pay = payment(mid, periods, presentValue);
      if (pay < payment) {
        l = mid;
      } else {
        r = mid;
      }
    }
    return r;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    double amount = Double.parseDouble(request.getParameter("amount"));
    double fees = Double.parseDouble(request.getParameter("fees"));
    double interest = Double.parseDouble(request.getParameter("interest"));
    int years = Integer.parseInt(request.getParameter("years"));
    log.info(
        String.format(
            "amount: %f, fees: %f, interest: %f, years: %d", amount, fees, interest, years));

    double monthlyPayment = payment(interest / 12, years * 12, amount);
    response
        .getWriter()
        .println(String.format("Monthly payment: %.2f <br>", monthlyPayment));
    double apr = rate(monthlyPayment, years * 12, amount - fees) * 12;
    response.getWriter().println(String.format("APR: %.3f%% <br>", 100 * apr));
  }
}
// [END example]
