package com.fit.scroller;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        //About US
        TextView aboutustext = (TextView) findViewById(R.id.aboutustext);
        aboutustext.setText("For nearly 40 years, Cross Country Automotive Services has been a pioneer and thought leader  "+
"in creating and delivering technology-enabled service solutions for the auto and insurance industries. \n\n"+
"Cross Country manages roadside assistance programs in the U.S. on behalf of global automobile  "+
"manufacturers and U.S. insurance carriers, providing solutions for the nearly 6 million  "+
"roadside service events experienced by their consumers each year.  In addition, Cross Country  "+
"provides accident scene management, vehicle release management and total loss screening services  "+
"for insurance carriers, and customer care services for a number of automotive manufacturers.");
        
        //Terms and Conditions
        TextView termstext = (TextView) findViewById(R.id.termsandconditionstext);
        termstext.setText("\nPlease read these Terms of Service carefully before downloading, or using, this application. " +
"These terms apply to your use of the mobile phone application for Roadside Connect� an application  " +
"to be used by participants in Cross Country Service Corp.誷 network of independent service  " +
"providers (the 襂SP Network� for the provision of towing and emergency roadside services on  " +
"behalf of Cross Country, its affiliates and their respective clients and consumers.  The application " +
"provides automated features to assist members of the ISP Network in the provision of such " +
"towing and emergency roadside services (the 褹pplication�. " +
"\n\nBy downloading the Application, or using the Application in any way, you agree to and are " +
"bound by the terms and conditions set forth in this document and in any changes thereto that  " +
"Cross Country may publish from time to time (collectively, the \"Terms of Service\"). If you do " +
"not agree to all of the terms and conditions contained in the Terms of Service, you may not access " +
"or use the Application.  " +
"\n\nCross Country may change the Terms of Service and other guidelines and rules posted on the Argosi.net  " +
"website (or its successor website) from time to time at its sole discretion. Your continued use  " +
"of the Application constitutes your acceptance of the changes. Your access and use of the  " +
"Application will be subject to the most current version of the Terms of Service, rules and  " +
"guidelines posted on the Application at the time of such use.  If you breach any of the Terms of  " +
"Service, your authorization to access or use the Application automatically terminates. " +
"\n\nAll rights that are not expressly granted under these Terms of Service are reserved by Cross Country. " +
"\n\nServices Description\n\n" +
"The Application provides members of the ISP Network with the ability to send and receive status  " +
"and location information while performing towing and emergency roadside services on behalf of  " +
"Cross Country.  As a member of the ISP Network, you may use the Application in connection with  " +
"your provision of towing and emergency roadside assistance services on behalf of Cross Country,  " +
"its affiliates and their respective clients and consumers.  No other use of the Application is  " +
"granted hereby. " +
"\n\nUsage License\n\n" +
"Cross Country hereby grants you a non-exclusive, non-transferable license to download, install  " +
"and use the Application on one or more mobile devices according to these Terms of Service.  You  " +
"agree not to reproduce, redistribute, reverse engineer or use the Application by any means or for " +
"any purposes other than in accordance with the Terms of Service.  You agree that the Application  " +
"contains proprietary content, information and material that is owned by Cross Country and its " +
"affiliates, and is protected by applicable intellectual property and other laws, including but " +
"not limited to copyright, and that you will not use such proprietary content, information or " +
"materials in any way whatsoever except for permitted use.  No portion of the Application may be " +
"reproduced in any form or by any means. You agree not to modify, rent, lease, loan, sell, distribute,  " +
"or create derivative works based on the Application, in any manner, and you shall not exploit  " +
"the Application in any unauthorized way whatsoever, including but not limited to, by trespass or  " +
"burdening network capacity. You further agree not to use the Application in any manner to harass, " +
"abuse, stalk, threaten, defame or otherwise infringe or violate the rights of any other party, and  " +
"that Cross Country is not in any way responsible for any such use by you, nor for any harassing, " +
"threatening, defamatory, offensive or illegal messages or transmissions that you may receive as a  " +
"result of using any of the Application. " +
"You agree that Cross Country retains all right, title and interest in the Application,  " +
"including all intellectual property rights related thereto.  You agree not to remove, alter, " +
"or otherwise obscure any trademark, copyright or other proprietary rights notices contained  " +
"within or displayed by the Application. " +
"\n\nLimited Right to Use\n\n " +
"Your use of the Application is limited solely for your provision of towing and emergency  " +
"roadside assistance services on behalf of Cross Country, its affiliates and their respective " + 
"clients and consumers.  You shall not use the Application for any other purpose, including  " +
"without limitation for the provision of towing or roadside services to or on behalf of other companies. " +
"\n\nThird Party Services\n\n" +
"The Application invokes a number of applications that were not developed by Cross Country and  " +
"that are not under the control of Cross Country.  For example, your mobile phone誷 browser,  " +
"email and SMS programs, dialer and other applications.  Cross Country cannot assure you that  " +
"these third party applications will function and must expressly deny any liability related  " +
"to such third party applications." +
"\n\nFees, Costs and Other Expenses\n\n" +
"The Application makes use of a data network operated by your wireless service provider  " +
"to send data, location and recorded audio from your device to our servers and call centers, " +
"and to serve information back to you.  Depending on your data plan, you may incur charges from  " +
"your wireless service provider for use of their network and/or for specific services such as  " +
"networks and/or for specific services such as making phone calls, sending or receiving text  " +
"messages and/or emails or other services. \n " +
"You are solely responsible for any and all costs you incur as a result of your usage " +
"of the Application." +
"\n\nDisclaimer of Warranties; Limitation on Liability \n\n" +
"YOU EXPRESSLY ACKNOWLEDGE AND AGREE THAT USE OF THE APPLICATION IS AT YOUR SOLE RISK AND  " +
"THAT THE ENTIRE RISK AS TO SATISFACTORY QUALITY, PERFORMANCE, ACCURACY AND EFFORT IS WITH  " +
"YOU. TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, THE APPLICATION IS PROVIDED \"AS IS\"  " +
"AND \"AS AVAILABLE\", WITH ALL FAULTS AND WITHOUT WARRANTY OF ANY KIND, AND CROSS COUNTRY AND  " +
"ITS AFFILIATES (COLLECTIVELY REFERRED TO AS \"CROSS COUNTRY\" FOR THE PURPOSES OF THIS SECTION) " +
"HEREBY DISCLAIM ALL WARRANTIES AND CONDITIONS WITH RESPECT TO THE APPLICATION, EITHER EXPRESS,  " +
"IMPLIED OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES AND/OR CONDITIONS OF  " +
"MERCHANTABILITY, SATISFACTORY QUALITY, FITNESS FOR A PARTICULAR PURPOSE, ACCURACY, QUIET ENJOYMENT, " +
"AND NON-INFRINGEMENT OF THIRD PARTY RIGHTS. NO ORAL OR WRITTEN INFORMATION OR ADVICE GIVEN BY  " +
"CROSS COUNTRY OR A CROSS COUNTRY AUTHORIZED REPRESENTATIVE SHALL CREATE A WARRANTY. SHOULD  " +
"THE APPLICATION PROVE DEFECTIVE, YOU ASSUME THE ENTIRE COST OF ALL NECESSARY SERVICING,  " +
"REPAIR OR CORRECTION.  " +
"TO THE EXTENT NOT PROHIBITED BY LAW, IN NO EVENT SHALL CROSS COUNTRY BE LIABLE FOR  " +
"PERSONAL INJURY, OR ANY INCIDENTAL, SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES WHATSOEVER,  " +
"INCLUDING, WITHOUT LIMITATION, DAMAGES FOR LOSS OF PROFITS, CORRUPTION OR LOSS OF DATA, FAILURE  " +
"TO TRANSMIT OR RECEIVE ANY DATA, BUSINESS INTERRUPTION OR ANY OTHER COMMERCIAL DAMAGES OR LOSSES,  " +
"ARISING OUT OF OR RELATED TO YOUR USE OF OR INABILITY TO USE THE APPLICATION, HOWEVER CAUSED, " +
"REGARDLESS OF THE THEORY OF LIABILITY (CONTRACT, TORT OR OTHERWISE) AND EVEN IF CROSS COUNTRY  " +
"HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. SOME JURISDICTIONS DO NOT ALLOW THE  " +
"LIMITATION OF LIABILITY FOR PERSONAL INJURY, OR OF INCIDENTAL OR CONSEQUENTIAL DAMAGES,  " +
"SO THIS LIMITATION MAY NOT APPLY TO YOU. In no event shall Cross Country誷 total liability to you  " +
"for all damages (other than as may be required by applicable law in cases involving personal injury) " +
"exceed the amount of fifty dollars ($50.00). The foregoing limitations will apply even if the " +
"above stated remedy fails of its essential purpose. " +
"\n\nUse of Data\n\n" +
" (a) You agree that Cross Country and its affiliates may collect and use technical and  " +
" related information, including but not limited to information about your mobile phone,  " +
" computer, system and application software, and peripherals, that is gathered periodically  " +
" to facilitate the provision of software updates, product support and other services to you " +
" (if any) related to the Application, and to verify compliance with the terms of these Terms of  " +
" Service. Cross Country may also use this information to improve its products or to provide  " +
"services or technologies to you, its clients and their consumers. \n\n " +
"(b) The services provided through the Application rely upon location information. To provide  " +
"these services, Cross Country, its affiliates and their partners may transmit, collect, maintain,  " +
"process and use your location data, including the real-time geographic location of your mobile phone.  " +
"By using the Application, you agree and consent to Cross Country, its affiliates and their partners'  " +
"transmission, collection, maintenance, processing and use of your location data to provide you with " +
"services. The location data is collected in a form that personally identifies you or your vehicles.  \n\n" +
"(c) You agree and consent that Cross Country and its affiliates may use the location information  " +
"as well as other data that Cross Country receives from your use of the Application for commercial  " +
"use with their clients, customers, motor club members, third party vendors and with other parties  " +
"as Cross Country may determine from time to time.  \n\n" +
"(d) You agree and consent to Cross Country and its affiliates�use of your location  " +
"information provided through your use of the Application to inform you of products and  " +
"services made available from marketing partners, often with special network member benefits." +
"\n\nTerm and Termination\n\n" +
"These Terms of Service become effective immediately when you are granted access to the  " +
"Application and shall remain in effect until your use of the Application is terminated. " +
"You may terminate your access to the Application at any time.  Cross Country reserves the " +
"right to terminate these Terms of Service or your right to use the Application at any time  " +
"and for any purpose upon notice.  Upon termination, your right to use the Application shall cease. " +
"These Terms of Service regarding limits on liability and voluntary submissions shall survive  " +
"the termination of these Terms of Service, and shall remain in effect indefinitely. " +
"The text and images used in the Service or Application are the property of, or licensed by,  " +
"Cross Country and subject to copyright and other intellectual property protection.  The client  " +
"names, logos and service marks are registered trademarks of their respective owners and may  " +
"not be modified, and they may not be used, downloaded, copied or distributed in any way  " +
"except as an integral part of the authorized download, copy or transmission of materials  " +
"in the Application.  No license to any Cross Country or its clients�intellectual property  " +
"has been granted by this Service or Application." +
"\n\nMiscellaneous\n\n" +
"These Terms of Service and any action related to them shall be governed, controlled,  " +
"interpreted and defined by and under the laws of the Commonwealth of Massachusetts.   " +
"The exclusive jurisdiction and venue of any action with respect to the subject matter of  " +
"the Application or these Terms of Service shall be in the state courts of the Commonwealth  " +
"of Massachusetts. \n\n" +
"These Terms of Service constitutes the entire agreement between the parties with respect  " +
"to the use of the Application and supersedes all prior or contemporaneous understandings  " +
"regarding such subject matter. No amendment to or modification of this License will be  " +
"binding unless in writing and signed by Cross Country.  \n\n" +
"�2011 Cross Country Automotive Services, Inc. All rights reserved.   ");
    }
}