<body>
 <% double num1=0,num2=0,add=0;int flag=0; 
        String tNum1=request.getParameter("in1");
      String tNum2=request.getParameter("in2");
      String tFlag=request.getParameter("flag");
      if(tFlag!=null &&!tFlag.equals("")){
         flag=Integer.parseInt(tFlag);
      }
      if(tNum1!=null && tNum2!=null && !tNum1.equals("") && !tNum2.equals("")){
        num1=Double.parseDouble(tNum1);
        num2=Double.parseDouble(tNum2);
        switch(flag){
            case 0:add=num1+num2;break;
            case 1:add=num1-num2;break;
            case 2:add=num1*num2;break;
            case 3:if(num2==0)
                        add=0;
                    else
                        add=num1/num2;
                    break;      

        }
            }
   %>
     <form action="" method="post">
    <input type="text" name="in1" value="<%=num1 %>"/>
    <%switch(flag){
        case 0:
    %>
    <select name="flag">
        <option value="0" selected>+</option>
        <option value="1" >-</option>
        <option value="2" >*</option>
        <option value="3"> /</option>
    </select>
   <%   break;
   case 1:
    %>
    <select name="flag">
        <option value="0" >+</option>
        <option value="1"  selected>-</option>
        <option value="2" >*</option>
        <option value="3"> /</option>
    </select>
    <%  break;
   case 2:
    %>
    <select name="flag">
        <option value="0" >+</option>
        <option value="1" >-</option>
        <option value="2" selected >*</option>
        <option value="3"> /</option>
    </select>
     <% break;
   case 3:
    %>
    <select name="flag">
        <option value="0" >+</option>
        <option value="1" >-</option>
        <option value="2" >*</option>
        <option value="3" selected> /</option>
    </select>
   <%}%>
    <input type="text" name="in2" value="<%=num2 %>"/>
    <input type="submit" name="dengyu" value="="/>
    <input type="text" value="<%=add %>"/>
    </form>
  </body>
