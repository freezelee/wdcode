<div class="memberInfo">
	<div class="top"></div>
	<div class="middle">
		<p>欢迎您!&nbsp;&nbsp;<span class="username">${token.name}</span>&nbsp;&nbsp;[<a class="userLogout" href="<@s.url action="logout" />"">退出</a>]</p>
	</div>
	<div class="bottom"></div>
</div>
<div class="blank"></div>
<div class="memberMenu">
	<div class="top">
		<a href="<@s.url action="user_to_center" />">会员中心首页</a>
	</div>
	<div class="middle">
		<ul>
              	<li class="order">
                  	<ul>
                      	<li><@s.a action="orders_list" >我的订单</@s.a></li>
                      </ul>
                  </li>
                  <li class="category favorite">
                  	<ul>
                  		<li><@s.a action="favorite_list" >商品收藏</@s.a></li>
                  		<li><@s.a action="notify_list" >缺货登记</@s.a></li> 
                      </ul>
                  </li>
                	<li class="message">
                  	<ul>
                      	<li><a href="<@s.url action="message_to_send"/>">发送消息</a></li>
                          <li><a href="<@s.url action="message_to_inbox"/>">收件箱</a></li>
                          <li><a href="<@s.url action="message_to_outbox"/>">发件箱</a></li>
                      </ul>
                  </li>
                  <li class="profile">
                  	<ul>
                      	<li><a href="<@s.url action="user_to_info"/>">个人信息</a></li>
                          <li><a href="<@s.url action="password_to_info"/>">修改密码</a></li>
                          <li><@s.a action="receiver_list" >收货地址</@s.a></li>  
                      </ul>
                  </li>
                  <li class="deposit">
                  	<ul>
                  		<li><@s.a action="money_list" >我的预存款</@s.a></li>   
                      	<li><a href="<@s.url action="money_to_recharge"/>">预存款充值</a></li>
                      </ul>
                  </li>
              </ul>
	</div>
	<div class="bottom"></div>
</div>