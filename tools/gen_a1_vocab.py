# Generates A1VocabSeedHelper.java
topics = {
"Daily life": [
("routine","/ruːˈtiːn/","/ruːˈtiːn/","noun","thói quen hàng ngày","lịch sinh hoạt","I have a healthy routine.","Tôi có thói quen lành mạnh.","daily routine","Đọc ru-tine, không 3 âm rời."),
("alarm","/əˈlɑːm/","/əˈlɑːrm/","noun","báo thức","đồng hồ báo thức","I set my alarm at six.","Tôi đặt báo thức lúc sáu giờ.","set the alarm","Nhấn âm larm."),
("shower","/ˈʃaʊ.ər/","/ˈʃaʊ.ɚ/","noun","vòi sen","tắm vòi sen","I take a shower every morning.","Tôi tắm vòi sen mỗi sáng.","take a shower","Phải có a: take a shower."),
("laundry","/ˈlɔːn.dri/","/ˈlɔːn.dri/","noun","việc giặt giũ","giặt quần áo","I do the laundry on Sunday.","Tôi giặt đồ Chủ nhật.","do the laundry","Do the laundry, không wash laundry."),
("dishes","/ˈdɪʃ.ɪz/","/ˈdɪʃ.ɪz/","noun","bát đĩa","rửa bát","I wash the dishes after dinner.","Tôi rửa bát sau bữa tối.","wash the dishes","Dishes số nhiều."),
("chore","/tʃɔːr/","/tʃɔːr/","noun","việc vặt","việc nhà","Cleaning is a boring chore.","Dọn dẹp là việc vặt chán.","household chore","Chore phát âm /tʃɔːr/."),
("appointment","/əˈpɔɪnt.mənt/","/əˈpɔɪnt.mənt/","noun","cuộc hẹn","hẹn gặp","I have a dentist appointment.","Tôi hẹn nha sĩ.","make an appointment","Nhấn point."),
("schedule","/ˈʃed.juːl/","/ˈskedʒ.uːl/","noun","lịch trình","thời khóa biểu","My schedule is busy today.","Lịch hôm nay bận.","busy schedule","UK/US khác phát âm."),
("rest","/rest/","/rest/","noun","nghỉ ngơi","nghỉ","You need some rest.","Bạn cần nghỉ ngơi.","take a rest","Rest = nghỉ."),
("prepare","/prɪˈpeər/","/prɪˈper/","verb","chuẩn bị","nấu/chuẩn bị","I prepare lunch for my family.","Tôi chuẩn bị bữa trưa.","prepare food","Prepare ≠ repair."),
("tidy","/ˈtaɪ.di/","/ˈtaɪ.di/","adjective","ngăn nắp","gọn gàng","Keep your room tidy.","Giữ phòng ngăn nắp.","tidy up","Tidy up = dọn dẹp."),
("relax","/rɪˈlæks/","/rɪˈlæks/","verb","thư giãn","nghỉ thư giãn","I relax by reading.","Tôi thư giãn bằng đọc sách.","relax at home","Relax không cần myself."),
("weekday","/ˈwiːk.deɪ/","/ˈwiːk.deɪ/","noun","ngày thường","T2-T6","I work on weekdays.","Tôi làm việc ngày thường.","on weekdays","Weekday ≠ weekend."),
],
"Home and furniture": [
("sofa","/ˈsəʊ.fə/","/ˈsoʊ.fə/","noun","ghế sofa","phòng khách","We sit on the sofa.","Chúng tôi ngồi sofa.","comfortable sofa","Sofa /ˈsoʊ.fə/."),
("wardrobe","/ˈwɔː.drəʊb/","/ˈwɔːr.droʊb/","noun","tủ quần áo","phòng ngủ","My clothes are in the wardrobe.","Quần áo trong tủ.","open the wardrobe","Ward-robe 2 âm."),
("curtain","/ˈkɜː.tən/","/ˈkɜːr.tən/","noun","rèm cửa","cửa sổ","Please close the curtains.","Đóng rèm giúp.","close the curtains","Curtain thường số nhiều."),
("mirror","/ˈmɪr.ər/","/ˈmɪr.ɚ/","noun","gương","soi gương","She looks in the mirror.","Cô ấy soi gương.","look in the mirror","Mirror /ˈmɪr.ər/."),
("shelf","/ʃelf/","/ʃelf/","noun","kệ","đặt sách","The books are on the shelf.","Sách trên kệ.","on the shelf","Shelf → shelves số nhiều."),
("drawer","/drɔːr/","/drɔːr/","noun","ngăn kéo","bàn/tủ","Put it in the drawer.","Bỏ vào ngăn kéo.","top drawer","Drawer /drɔːr/."),
("blanket","/ˈblæŋ.kɪt/","/ˈblæŋ.kɪt/","noun","chăn","giường","I need a warm blanket.","Tôi cần chăn ấm.","warm blanket","Blanket không đếm được kiểu lượng."),
("pillow","/ˈpɪl.oʊ/","/ˈpɪl.oʊ/","noun","gối","ngủ","This pillow is soft.","Gối này mềm.","soft pillow","Pillow có a/an."),
("lamp","/læmp/","/læmp/","noun","đèn","bàn/đầu giường","Turn on the lamp.","Bật đèn lên.","desk lamp","Turn on/off the lamp."),
("carpet","/ˈkɑː.pɪt/","/ˈkɑːr.pɪt/","noun","thảm","sàn nhà","There is a carpet in the living room.","Có thảm phòng khách.","on the carpet","Carpet thường không đếm được."),
("balcony","/ˈbæl.kə.ni/","/ˈbæl.kə.ni/","noun","ban công","căn hộ","We have coffee on the balcony.","Uống cà phê ban công.","on the balcony","Balcony /ˈbæl.kə.ni/."),
("basement","/ˈbeɪs.mənt/","/ˈbeɪs.mənt/","noun","tầng hầm","nhà","The washing machine is in the basement.","Máy giặt ở tầng hầm.","in the basement","Base-ment."),
("garage","/ˈɡær.ɑːʒ/","/ɡəˈrɑːʒ/","noun","nhà để xe","garage","He parks the car in the garage.","Anh ấy đậu xe trong garage.","in the garage","Garage UK/US khác âm."),
],
"Places in town": [
("pharmacy","/ˈfɑː.mə.si/","/ˈfɑːr.mə.si/","noun","nhà thuốc","mua thuốc","The pharmacy opens at eight.","Nhà thuốc mở lúc tám giờ.","near the pharmacy","Pharmacy ≠ hospital."),
("library","/ˈlaɪ.brər.i/","/ˈlaɪ.brer.i/","noun","thư viện","đọc sách","I study in the library.","Tôi học ở thư viện.","public library","Library /ˈlaɪ.brər.i/."),
("post office","/pəʊst ˈɒf.ɪs/","/poʊst ˈɑː.fɪs/","noun","bưu điện","gửi thư","I went to the post office.","Tôi đi bưu điện.","at the post office","Post office 2 từ."),
("supermarket","/ˈsuː.pə.mɑː.kɪt/","/ˈsuː.pər.mɑːr.kɪt/","noun","siêu thị","mua đồ","We shop at the supermarket.","Mua sắm siêu thị.","local supermarket","Supermarket một từ."),
("bakery","/ˈbeɪ.kər.i/","/ˈbeɪ.kər.i/","noun","tiệm bánh","mua bánh","I buy bread at the bakery.","Tôi mua bánh mì tiệm bánh.","local bakery","Bakery /ˈbeɪ.kər.i/."),
("clinic","/ˈklɪn.ɪk/","/ˈklɪn.ɪk/","noun","phòng khám","khám bệnh","There is a clinic near here.","Có phòng khám gần đây.","near the clinic","Clinic nhỏ hơn hospital."),
("town hall","/taʊn hɔːl/","/taʊn hɔːl/","noun","tòa thị chính","trung tâm","The town hall is in the center.","Tòa thị chính ở trung tâm.","at the town hall","Town hall 2 từ."),
("crossroads","/ˈkrɒs.rəʊdz/","/ˈkrɔːs.roʊdz/","noun","ngã tư","giao lộ","Turn left at the crossroads.","Rẽ trái ngã tư.","at the crossroads","Crossroads thường số nhiều."),
("pedestrian","/pəˈdes.tri.ən/","/pəˈdes.tri.ən/","noun","người đi bộ","vỉa hè","Pedestrians use the sidewalk.","Người đi bộ dùng vỉa hè.","pedestrian crossing","Pedestrian /pəˈdes.tri.ən/."),
("neighborhood","/ˈneɪ.bə.hʊd/","/ˈneɪ.bər.hʊd/","noun","khu phố","nơi ở","I like my neighborhood.","Tôi thích khu phố mình.","quiet neighborhood","Neighborhood US spelling."),
("corner","/ˈkɔː.nər/","/ˈkɔːr.nər/","noun","góc phố","vị trí","The cafe is on the corner.","Quán cà phê ở góc phố.","on the corner","On the corner = góc phố."),
("market","/ˈmɑː.kɪt/","/ˈmɑːr.kɪt/","noun","chợ","mua rau","She goes to the market daily.","Cô ấy đi chợ hàng ngày.","local market","Market ≠ supermarket."),
("bridge","/brɪdʒ/","/brɪdʒ/","noun","cây cầu","qua sông","Cross the bridge carefully.","Băng cầu cẩn thận.","cross the bridge","Bridge /brɪdʒ/."),
],
"Travel": [
("passport","/ˈpæs.pɔːt/","/ˈpæs.pɔːrt/","noun","hộ chiếu","xuất nhập cảnh","Show your passport, please.","Xuất trình hộ chiếu.","valid passport","Passport bắt buộc khi bay quốc tế."),
("luggage","/ˈlʌɡ.ɪdʒ/","/ˈlʌɡ.ɪdʒ/","noun","hành lý","sân bay","My luggage is heavy.","Hành lý nặng.","carry-on luggage","Luggage không đếm được."),
("suitcase","/ˈsuːt.keɪs/","/ˈsuːt.keɪs/","noun","vali","đựng quần áo","I pack my suitcase tonight.","Tôi xếp vali tối nay.","pack a suitcase","Suitcase đếm được."),
("boarding pass","/ˈbɔː.dɪŋ pɑːs/","/ˈbɔːr.dɪŋ pæs/","noun","thẻ lên máy bay","check-in","Here is your boarding pass.","Đây là thẻ lên máy bay.","show boarding pass","Boarding pass 2 từ."),
("customs","/ˈkʌs.təmz/","/ˈkʌs.təmz/","noun","hải quan","nhập cảnh","We went through customs.","Chúng tôi qua hải quan.","customs officer","Customs số nhiều."),
("delay","/dɪˈleɪ/","/dɪˈleɪ/","noun","sự trễ","chuyến bay","The flight has a delay.","Chuyến bay bị trễ.","flight delay","Delay vừa N vừa V."),
("abroad","/əˈbrɔːd/","/əˈbrɔːd/","adverb","ở nước ngoài","du lịch","She wants to study abroad.","Cô ấy muốn du học.","go abroad","Abroad không có s."),
("guidebook","/ˈɡaɪd.bʊk/","/ˈɡaɪd.bʊk/","noun","sách hướng dẫn","du lịch","I bought a guidebook.","Tôi mua sách hướng dẫn.","travel guidebook","Guidebook một từ."),
("map","/mæp/","/mæp/","noun","bản đồ","chỉ đường","Can I see the map?", "Cho tôi xem bản đồ?","city map","Map đếm được."),
("platform","/ˈplæt.fɔːm/","/ˈplæt.fɔːrm/","noun","sân ga","tàu hỏa","The train leaves from platform two.","Tàu đi từ sân ga 2.","train platform","Platform /ˈplæt.fɔːm/."),
("reservation","/ˌrez.əˈveɪ.ʃən/","/ˌrez.ərˈveɪ.ʃən/","noun","đặt chỗ","khách sạn","I have a reservation.","Tôi đã đặt chỗ.","make a reservation","Reservation /ˌrez.ərˈveɪ.ʃən/."),
("souvenir","/ˌsuː.vəˈnɪər/","/ˌsuː.vəˈnɪr/","noun","quà lưu niệm","du lịch","I bought a souvenir.","Tôi mua quà lưu niệm.","buy a souvenir","Souvenir /ˌsuː.vəˈnɪr/."),
("visa","/ˈviː.zə/","/ˈviː.zə/","noun","thị thực","nhập cảnh","Do I need a visa?", "Tôi cần visa không?","apply for a visa","Visa /ˈviː.zə/."),
],
"Shopping": [
("discount","/ˈdɪs.kaʊnt/","/ˈdɪs.kaʊnt/","noun","giảm giá","mua sắm","There is a ten percent discount.","Giảm mười phần trăm.","get a discount","Discount /ˈdɪs.kaʊnt/."),
("receipt","/rɪˈsiːt/","/rɪˈsiːt/","noun","hóa đơn","thanh toán","Keep your receipt.","Giữ hóa đơn.","show the receipt","Receipt /rɪˈsiːt/."),
("cashier","/kæˈʃɪər/","/kæˈʃɪr/","noun","thu ngân","quầy","Pay at the cashier.","Thanh toán ở quầy.","at the cashier","Cashier /kæˈʃɪr/."),
("fitting room","/ˈfɪt.ɪŋ ruːm/","/ˈfɪt.ɪŋ ruːm/","noun","phòng thử","quần áo","The fitting room is there.","Phòng thử ở kia.","in the fitting room","Fitting room 2 từ."),
("size","/saɪz/","/saɪz/","noun","cỡ/size","quần áo","What size do you wear?", "Bạn mặc size nào?","medium size","Size + S/M/L."),
("refund","/ˈriː.fʌnd/","/ˈriː.fʌnd/","noun","hoàn tiền","trả hàng","Can I get a refund?", "Tôi được hoàn tiền không?","ask for a refund","Refund /ˈriː.fʌnd/."),
("bargain","/ˈbɑː.ɡɪn/","/ˈbɑːr.ɡɪn/","noun","món hời","giá rẻ","This shirt is a bargain.","Áo này rẻ đấy.","good bargain","Bargain /ˈbɑːr.ɡɪn/."),
("customer","/ˈkʌs.tə.mər/","/ˈkʌs.tə.mər/","noun","khách hàng","cửa hàng","The customer is waiting.","Khách đang đợi.","regular customer","Customer /ˈkʌs.tə.mər/."),
("change","/tʃeɪndʒ/","/tʃeɪndʒ/","noun","tiền thối","trả tiền","Here is your change.","Đây là tiền thối.","keep the change","Change = tiền thối hoặc thay đổi."),
("brand","/brænd/","/brænd/","noun","thương hiệu","sản phẩm","I like this brand.","Tôi thích hãng này.","famous brand","Brand /brænd/."),
("checkout","/ˈtʃek.aʊt/","/ˈtʃek.aʊt/","noun","quầy thanh toán","siêu thị","Meet me at checkout.","Gặp tôi quầy thanh toán.","at checkout","Checkout một từ."),
("wallet","/ˈwɒl.ɪt/","/ˈwɑː.lɪt/","noun","ví","đựng tiền","I lost my wallet.","Tôi mất ví.","leather wallet","Wallet /ˈwɑː.lɪt/ US."),
("try on","/traɪ ɒn/","/traɪ ɑːn/","phrase","thử (đồ)","cửa hàng","Can I try on this jacket?", "Thử áo khoác được không?","try on clothes","Try on = thử đồ."),
],
"Weather": [
("forecast","/ˈfɔː.kɑːst/","/ˈfɔːr.kæst/","noun","dự báo","thời tiết","Check the weather forecast.","Xem dự báo thời tiết.","weather forecast","Forecast /ˈfɔːr.kæst/."),
("temperature","/ˈtem.prə.tʃər/","/ˈtem.prə.tʃər/","noun","nhiệt độ","đo thời tiết","The temperature is thirty degrees.","Nhiệt độ ba mươi độ.","high temperature","Temperature /ˈtem.prə.tʃər/."),
("humid","/hjuːˈmɪd/","/hjuːˈmɪd/","adjective","ẩm ướt","khí hậu","It is hot and humid today.","Hôm nay nóng ẩm.","humid weather","Humid /hjuːˈmɪd/."),
("storm","/stɔːm/","/stɔːrm/","noun","bão/giông","thời tiết xấu","A storm is coming.","Bão sắp đến.","heavy storm","Storm /stɔːrm/."),
("lightning","/ˈlaɪt.nɪŋ/","/ˈlaɪt.nɪŋ/","noun","sét","giông bão","I saw lightning last night.","Tối qua có sét.","lightning and thunder","Lightning không đếm được."),
("foggy","/ˈfɒɡ.i/","/ˈfɑː.ɡi/","adjective","có sương mù","tầm nhìn kém","It is foggy this morning.","Sáng nay có sương mù.","foggy day","Foggy từ fog."),
("breeze","/briːz/","/briːz/","noun","gió nhẹ","thoảng","A cool breeze is blowing.","Gió mát thổi.","cool breeze","Breeze /briːz/."),
("forecast","/ˈfɔː.kɑːst/","/ˈfɔːr.kæst/","noun","dự báo","thời tiết","The forecast says rain.","Dự báo báo mưa.","daily forecast","Duplicate removed below"),
],
}

# fix duplicate forecast in weather - replace last entries
topics["Weather"] = [
("forecast","/ˈfɔː.kɑːst/","/ˈfɔːr.kæst/","noun","dự báo","thời tiết","Check the weather forecast.","Xem dự báo thời tiết.","weather forecast","Forecast /ˈfɔːr.kæst/."),
("temperature","/ˈtem.prə.tʃər/","/ˈtem.prə.tʃər/","noun","nhiệt độ","đo thời tiết","The temperature is thirty degrees.","Nhiệt độ ba mươi độ.","high temperature","Temperature /ˈtem.prə.tʃər/."),
("humid","/hjuːˈmɪd/","/hjuːˈmɪd/","adjective","ẩm ướt","khí hậu","It is hot and humid today.","Hôm nay nóng ẩm.","humid weather","Humid /hjuːˈmɪd/."),
("storm","/stɔːm/","/stɔːrm/","noun","bão/giông","thời tiết xấu","A storm is coming.","Bão sắp đến.","heavy storm","Storm /stɔːrm/."),
("lightning","/ˈlaɪt.nɪŋ/","/ˈlaɪt.nɪŋ/","noun","sét","giông bão","I saw lightning last night.","Tối qua có sét.","lightning and thunder","Lightning không đếm được."),
("foggy","/ˈfɒɡ.i/","/ˈfɑː.ɡi/","adjective","có sương mù","tầm nhìn kém","It is foggy this morning.","Sáng nay có sương mù.","foggy day","Foggy từ fog."),
("breeze","/briːz/","/briːz/","noun","gió nhẹ","thoảng","A cool breeze is blowing.","Gió mát thổi.","cool breeze","Breeze /briːz/."),
("cloudy","/ˈklaʊ.di/","/ˈklaʊ.di/","adjective","nhiều mây","trời u ám","It is cloudy today.","Hôm nay trời nhiều mây.","cloudy sky","Cloudy ≠ cloudy day sai ngữ pháp."),
("drought","/draʊt/","/draʊt/","noun","hạn hán","thiếu mưa","The drought lasted months.","Hạn kéo dài nhiều tháng.","severe drought","Drought /draʊt/."),
("forecast","/ˈfɔː.kɑːst/","/ˈfɔːr.kæst/","noun","dự báo","thời tiết","The forecast says rain.","Dự báo báo mưa.","daily forecast","SKIP"),
("umbrella","/ʌmˈbrel.ə/","/ʌmˈbrel.ə/","noun","ô/dù","mưa","Take an umbrella.","Mang theo ô.","open an umbrella","Umbrella có an."),
("degree","/dɪˈɡriː/","/dɪˈɡriː/","noun","độ (C/F)","nhiệt độ","It is thirty-five degrees.","Ba mươi lăm độ.","degrees Celsius","Degree /dɪˈɡriː/."),
("season","/ˈsiː.zən/","/ˈsiː.zən/","noun","mùa","bốn mùa","Spring is my favorite season.","Xuân là mùa tôi thích.","rainy season","Season /ˈsiː.zən/."),
]
# remove duplicate forecast skip
topics["Weather"] = [t for t in topics["Weather"] if t[0] != "forecast" or t[7] != "SKIP"] + []
seen = set()
weather_clean = []
for t in topics["Weather"]:
    if t[0] not in seen:
        seen.add(t[0])
        weather_clean.append(t)
topics["Weather"] = weather_clean[:13]

topics["Health"] = [
("headache","/ˈhed.eɪk/","/ˈhed.eɪk/","noun","đau đầu","triệu chứng","I have a headache.","Tôi đau đầu.","bad headache","Headache một từ."),
("sore throat","/sɔːr θroʊt/","/sɔːr θroʊt/","noun","đau họng","cảm cúm","She has a sore throat.","Cô ấy đau họng.","sore throat and cough","Sore throat 2 từ."),
("medicine","/ˈmed.ɪ.sən/","/ˈmed.ɪ.sən/","noun","thuốc","điều trị","Take this medicine twice a day.","Uống thuốc hai lần/ngày.","take medicine","Medicine không đếm được."),
("prescription","/prɪˈskrɪp.ʃən/","/prɪˈskrɪp.ʃən/","noun","đơn thuốc","bác sĩ kê","I need a prescription.","Tôi cần đơn thuốc.","doctor prescription","Prescription /prɪˈskrɪp.ʃən/."),
("symptom","/ˈsɪmp.təm/","/ˈsɪmp.təm/","noun","triệu chứng","bệnh","What are your symptoms?", "Triệu chứng của bạn?","common symptoms","Symptom → symptoms."),
("fever","/ˈfiː.vər/","/ˈfiː.vər/","noun","sốt","cảm","He has a high fever.","Anh ấy sốt cao.","high fever","Fever /ˈfiː.vər/."),
("cough","/kɒf/","/kɔːf/","noun","ho","cảm","I have a bad cough.","Tôi ho nhiều.","dry cough","Cough vừa N vừa V."),
("allergy","/ˈæl.ə.dʒi/","/ˈæl.ər.dʒi/","noun","dị ứng","thức ăn/pollen","I have a food allergy.","Tôi dị ứng thức ăn.","allergy to nuts","Allergy /ˈæl.ər.dʒi/."),
("exercise","/ˈek.sə.saɪz/","/ˈek.sər.saɪz/","noun","tập thể dục","sức khỏe","Exercise is good for you.","Tập thể dục tốt cho bạn.","regular exercise","Exercise không đếm được nghĩa chung."),
("healthy","/ˈhel.θi/","/ˈhel.θi/","adjective","khỏe mạnh","sức khỏe","Eat healthy food.","Ăn thức ăn lành mạnh.","healthy lifestyle","Healthy /ˈhel.θi/."),
("appointment","/əˈpɔɪnt.mənt/","/əˈpɔɪnt.mənt/","noun","cuộc hẹn","khám bệnh","I made a doctor appointment.","Tôi hẹn bác sĩ.","medical appointment","Appointment trùng daily life - use different word"),
]
# fix health duplicate appointment
topics["Health"][-1] = ("bandage","/ˈbæn.dɪdʒ/","/ˈbæn.dɪdʒ/","noun","băng y tế","vết thương","Put a bandage on the cut.","Băng lên vết cắt.","clean bandage","Bandage /ˈbæn.dɪdʒ/.")
topics["Health"] += [
("patient","/ˈpeɪ.ʃənt/","/ˈpeɪ.ʃənt/","noun","bệnh nhân","bệnh viện","The patient is resting.","Bệnh nhân đang nghỉ.","hospital patient","Patient /ˈpeɪ.ʃənt/."),
("dentist","/ˈden.tɪst/","/ˈden.tɪst/","noun","nha sĩ","răng","I visit the dentist twice a year.","Tôi khám răng hai lần/năm.","dentist appointment","Dentist /ˈden.tɪst/."),
]

topics["Feelings"] = [
("worried","/ˈwʌr.id/","/ˈwʌr.id/","adjective","lo lắng","cảm xúc","I am worried about the test.","Tôi lo bài kiểm tra.","feel worried","Worried /ˈwʌr.id/."),
("excited","/ɪkˈsaɪ.tɪd/","/ɪkˈsaɪ.tɪd/","adjective","hào hứng","vui mong đợi","I am excited about the trip.","Tôi hào hứng chuyến đi.","feel excited","Excited about, không for thường."),
("bored","/bɔːrd/","/bɔːrd/","adjective","chán","không hứng thú","I am bored in class.","Tôi chán trong lớp.","feel bored","Bored ≠ boring."),
("nervous","/ˈnɜː.vəs/","/ˈnɜːr.vəs/","adjective","hồi hộp/lo","trước sự kiện","She is nervous before exams.","Cô ấy hồi hộp trước thi.","feel nervous","Nervous /ˈnɜːr.vəs/."),
("proud","/praʊd/","/praʊd/","adjective","tự hào","thành tích","I am proud of you.","Tôi tự hào về bạn.","proud of","Proud of, không proud with."),
("lonely","/ˈloʊn.li/","/ˈloʊn.li/","adjective","cô đơn","cảm giác","He feels lonely sometimes.","Anh ấy đôi khi cô đơn.","feel lonely","Lonely ≠ alone."),
("confused","/kənˈfjuːzd/","/kənˈfjuːzd/","adjective","bối rối","không hiểu","I am confused by this lesson.","Tôi bối rối bài này.","feel confused","Confused by/about."),
("relaxed","/rɪˈlækst/","/rɪˈlækst/","adjective","thư thái","nghỉ ngơi","I feel relaxed at home.","Tôi thư thái ở nhà.","feel relaxed","Relaxed /rɪˈlækst/."),
("disappointed","/ˌdɪs.əˈpɔɪn.tɪd/","/ˌdɪs.əˈpɔɪn.tɪd/","adjective","thất vọng","kỳ vọng","I am disappointed with the result.","Tôi thất vọng kết quả.","disappointed with","Disappointed with/in."),
("grateful","/ˈɡreɪt.fəl/","/ˈɡreɪt.fəl/","adjective","biết ơn","cảm xúc tích cực","I am grateful for your help.","Tôi biết ơn sự giúp đỡ.","grateful for","Grateful for, không to."),
("embarrassed","/ɪmˈbær.əst/","/ɪmˈbær.əst/","adjective","xấu hổ","tình huống","I was embarrassed in class.","Tôi xấu hổ trong lớp.","feel embarrassed","Embarrassed /ɪmˈbær.əst/."),
("calm","/kɑːm/","/kɑːm/","adjective","bình tĩnh","cảm xúc","Stay calm, please.","Bình tĩnh nhé.","stay calm","Calm /kɑːm/."),
("surprised","/səˈpraɪzd/","/sərˈpraɪzd/","adjective","ngạc nhiên","bất ngờ","I was surprised by the news.","Tôi ngạc nhiên tin đó.","surprised by","Surprised by/at."),
]

topics["Jobs"] = [
("employee","/ɪmˈplɔɪ.iː/","/ɪmˈplɔɪ.iː/","noun","nhân viên","nơi làm việc","She is a company employee.","Cô ấy là nhân viên công ty.","full-time employee","Employee /ɪmˈplɔɪ.iː/."),
("employer","/ɪmˈplɔɪ.ər/","/ɪmˈplɔɪ.ər/","noun","người sử dụng lao động","công ty","My employer is fair.","Sếp công bằng.","good employer","Employer ≠ employee."),
("salary","/ˈsæl.ər.i/","/ˈsæl.əri/","noun","lương","công việc","His salary is good.","Lương anh ấy tốt.","monthly salary","Salary không đếm được."),
("colleague","/ˈkɒl.iːɡ/","/ˈkɑː.liːɡ/","noun","đồng nghiệp","văn phòng","My colleague is friendly.","Đồng nghiệp thân thiện.","work colleague","Colleague /ˈkɑː.liːɡ/ US."),
("interview","/ˈɪn.tə.vjuː/","/ˈɪn.tər.vjuː/","noun","phỏng vấn","xin việc","I have a job interview tomorrow.","Mai tôi phỏng vấn.","job interview","Interview /ˈɪn.tər.vjuː/."),
("resume","/ˈrez.juːmeɪ/","/ˈrez.ə.meɪ/","noun","CV/sơ yếu lý lịch","xin việc","Update your resume.","Cập nhật CV.","write a resume","Resume US, CV UK."),
("manager","/ˈmæn.ɪ.dʒər/","/ˈmæn.ɪ.dʒər/","noun","quản lý","công ty","Talk to the manager.","Nói chuyện với quản lý.","project manager","Manager /ˈmæn.ɪ.dʒər/."),
("part-time","/pɑːt taɪm/","/pɑːrt taɪm/","adjective","bán thời gian","công việc","I have a part-time job.","Tôi làm bán thời gian.","part-time work","Part-time gạch nối."),
("full-time","/fʊl taɪm/","/fʊl taɪm/","adjective","toàn thời gian","công việc","She works full-time.","Cô ấy làm toàn thời gian.","full-time job","Full-time gạch nối."),
("career","/kəˈrɪər/","/kəˈrɪr/","noun","sự nghiệp","dài hạn","Teaching is a good career.","Dạy học là nghề tốt.","career path","Career /kəˈrɪr/."),
("office","/ˈɒf.ɪs/","/ˈɑː.fɪs/","noun","văn phòng","làm việc","He works in an office.","Anh ấy làm văn phòng.","office job","Office /ˈɑː.fɪs/."),
("shift","/ʃɪft/","/ʃɪft/","noun","ca làm","lịch làm","My shift starts at six.","Ca tôi bắt đầu sáu giờ.","night shift","Shift /ʃɪft/."),
("retire","/rɪˈtaɪər/","/rɪˈtaɪr/","verb","nghỉ hưu","cuối sự nghiệp","He will retire next year.","Anh ấy nghỉ hưu năm sau.","retire from work","Retire /rɪˈtaɪr/."),
]

topics["Hobbies"] = [
("photography","/fəˈtɒɡ.rə.fi/","/fəˈtɑː.ɡrə.fi/","noun","nhiếp ảnh","sở thích","I enjoy photography.","Tôi thích chụp ảnh.","digital photography","Photography không đếm được."),
("guitar","/ɡɪˈtɑːr/","/ɡɪˈtɑːr/","noun","đàn guitar","nhạc","He plays the guitar.","Anh ấy chơi guitar.","play the guitar","Play the guitar có the."),
("badminton","/ˈbæd.mɪn.tən/","/ˈbæd.mɪn.tən/","noun","cầu lông","thể thao","We play badminton on Sundays.","Chơi cầu lông Chủ nhật.","play badminton","Badminton một từ."),
("chess","/tʃes/","/tʃes/","noun","cờ vua","trí tuệ","She is good at chess.","Cô ấy giỏi cờ vua.","play chess","Play chess không the."),
("knitting","/ˈnɪt.ɪŋ/","/ˈnɪt.ɪŋ/","noun","đan len","thủ công","My grandma likes knitting.","Bà tôi thích đan len.","enjoy knitting","Knitting /ˈnɪt.ɪŋ/."),
("hiking","/ˈhaɪ.kɪŋ/","/ˈhaɪ.kɪŋ/","noun","leo núi/đi bộ","ngoài trời","We go hiking every month.","Mỗi tháng đi bộ núi.","go hiking","Go hiking không a."),
("drawing","/ˈdrɔː.ɪŋ/","/ˈdrɔː.ɪŋ/","noun","vẽ tranh","sáng tạo","Drawing helps me relax.","Vẽ giúp tôi thư giãn.","love drawing","Drawing /ˈdrɔː.ɪŋ/."),
("collecting","/kəˈlekt.ɪŋ/","/kəˈlekt.ɪŋ/","noun","sưu tập","sở thích","He enjoys collecting stamps.","Anh ấy thích sưu tem.","stamp collecting","Collecting stamps."),
("cycling","/ˈsaɪ.klɪŋ/","/ˈsaɪ.klɪŋ/","noun","đạp xe","thể thao","Cycling is good exercise.","Đạp xe tốt cho sức khỏe.","go cycling","Go cycling."),
("gardening","/ˈɡɑː.dən.ɪŋ/","/ˈɡɑːr.dən.ɪŋ/","noun","làm vườn","sở thích","My mom loves gardening.","Mẹ thích làm vườn.","enjoy gardening","Gardening /ˈɡɑːr.dən.ɪŋ/."),
("volleyball","/ˈvɒl.i.bɔːl/","/ˈvɑː.li.bɔːl/","noun","bóng chuyền","thể thao","We play volleyball at school.","Chơi bóng chuyền ở trường.","play volleyball","Volleyball một từ."),
("puzzle","/ˈpʌz.əl/","/ˈpʌz.əl/","noun","câu đố/xếp hình","giải trí","I like doing puzzles.","Tôi thích xếp hình.","jigsaw puzzle","Puzzle /ˈpʌz.əl/."),
("craft","/krɑːft/","/kræft/","noun","thủ công","sáng tạo","She sells handmade crafts.","Cô ấy bán đồ thủ công.","handmade craft","Craft /kræft/."),
]

topics["Transportation"] = [
("subway","/ˈsʌb.weɪ/","/ˈsʌb.weɪ/","noun","tàu điện ngầm","đi lại","Take the subway to work.","Đi metro đi làm.","subway station","Subway US, underground UK."),
("platform","/ˈplæt.fɔːm/","/ˈplæt.fɔːrm/","noun","sân ga","tàu","Wait on platform three.","Đợi sân ga 3.","train platform","Platform trùng travel - ok different context"),
("fare","/feər/","/fer/","noun","giá vé","phương tiện","The bus fare is two dollars.","Giá vé xe buýt hai đô.","bus fare","Fare /fer/."),
("ticket","/ˈtɪk.ɪt/","/ˈtɪk.ɪt/","noun","vé","tàu/xe","I bought a train ticket.","Tôi mua vé tàu.","train ticket","Ticket /ˈtɪk.ɪt/."),
("highway","/ˈhaɪ.weɪ/","/ˈhaɪ.weɪ/","noun","xa lộ","ô tô","The highway is busy.","Xa lộ đông.","busy highway","Highway /ˈhaɪ.weɪ/."),
("traffic jam","/ˈtræf.ɪk dʒæm/","/ˈtræf.ɪk dʒæm/","noun","kẹt xe","giao thông","We are stuck in a traffic jam.","Chúng tôi kẹt xe.","heavy traffic jam","Traffic jam 2 từ."),
("helmet","/ˈhel.mɪt/","/ˈhel.mɪt/","noun","mũ bảo hiểm","xe máy","Wear a helmet, please.","Đội mũ bảo hiểm nhé.","wear a helmet","Helmet /ˈhel.mɪt/."),
("pedestrian crossing","/pəˈdes.tri.ən ˈkrɒs.ɪŋ/","/pəˈdes.tri.ən ˈkrɔː.sɪŋ/","noun","vạch sang đường","an toàn","Use the pedestrian crossing.","Dùng vạch sang đường.","at the crossing","Pedestrian crossing 2 từ."),
("fuel","/fjuːəl/","/ˈfjuː.əl/","noun","nhiên liệu","xe","Fuel prices are high.","Giá xăng cao.","fuel price","Fuel không đếm được."),
("parking","/ˈpɑː.kɪŋ/","/ˈpɑːr.kɪŋ/","noun","bãi đậu xe","ô tô","Parking is free here.","Đậu xe miễn phí ở đây.","parking lot","Parking /ˈpɑːr.kɪŋ/."),
("route","/ruːt/","/ruːt/","noun","tuyến đường","xe buýt","Bus route five goes downtown.","Tuyến 5 đi trung tâm.","bus route","Route /ruːt/ US."),
("departure","/dɪˈpɑː.tʃər/","/dɪˈpɑːr.tʃər/","noun","khởi hành","sân bay/ga","Departure is at ten a.m.","Khởi hành lúc mười giờ sáng.","departure time","Departure /dɪˈpɑːr.tʃər/."),
("arrival","/əˈraɪ.vəl/","/əˈraɪ.vəl/","noun","đến nơi","sân bay","Arrival gate is B2.","Cổng đến B2.","arrival gate","Arrival /əˈraɪ.vəl/."),
]

topics["Basic technology"] = [
("smartphone","/ˈsmɑːt.foʊn/","/ˈsmɑːrt.foʊn/","noun","điện thoại thông minh","công nghệ","I use my smartphone daily.","Tôi dùng smartphone hàng ngày.","new smartphone","Smartphone một từ."),
("password","/ˈpɑːs.wɜːd/","/ˈpæs.wɜːrd/","noun","mật khẩu","đăng nhập","Choose a strong password.","Chọn mật khẩu mạnh.","enter password","Password /ˈpæs.wɜːrd/."),
("Wi-Fi","/ˈwaɪ.faɪ/","/ˈwaɪ.faɪ/","noun","mạng không dây","internet","The hotel has free Wi-Fi.","Khách sạn có Wi-Fi miễn phí.","free Wi-Fi","Wi-Fi viết hoa W và F."),
("download","/ˌdaʊnˈləʊd/","/ˌdaʊnˈloʊd/","verb","tải xuống","ứng dụng","Download the app here.","Tải app ở đây.","download an app","Download /ˌdaʊnˈloʊd/."),
("upload","/ˌʌpˈləʊd/","/ˌʌpˈloʊd/","verb","tải lên","internet","Upload your photo.","Tải ảnh lên.","upload a file","Upload /ˌʌpˈloʊd/."),
("charger","/ˈtʃɑː.dʒər/","/ˈtʃɑːr.dʒər/","noun","sạc điện","điện thoại","I forgot my charger.","Tôi quên sạc.","phone charger","Charger /ˈtʃɑːr.dʒər/."),
("battery","/ˈbæt.ər.i/","/ˈbæt.əri/","noun","pin","thiết bị","My battery is low.","Pin yếu.","low battery","Battery /ˈbæt.əri/."),
("screen","/skriːn/","/skriːn/","noun","màn hình","điện tử","The screen is broken.","Màn hình hỏng.","touch screen","Screen /skriːn/."),
("keyboard","/ˈkiː.bɔːrd/","/ˈkiː.bɔːrd/","noun","bàn phím","máy tính","Use the keyboard to type.","Dùng bàn phím gõ.","computer keyboard","Keyboard /ˈkiː.bɔːrd/."),
("website","/ˈweb.saɪt/","/ˈweb.saɪt/","noun","trang web","internet","Visit our website.","Ghé trang web chúng tôi.","company website","Website một từ."),
("email","/ˈiː.meɪl/","/ˈiː.meɪl/","noun","email","liên lạc","Send me an email.","Gửi email cho tôi.","send an email","Email có an."),
("online","/ˌɒnˈlaɪn/","/ˌɑːnˈlaɪn/","adjective","trực tuyến","internet","I shop online sometimes.","Đôi khi mua online.","online shopping","Online /ˌɑːnˈlaɪn/."),
("application","/ˌæp.lɪˈkeɪ.ʃən/","/ˌæp.lɪˈkeɪ.ʃən/","noun","ứng dụng","điện thoại","Install this application.","Cài ứng dụng này.","mobile application","Application = app đầy đủ."),
]

total = sum(len(v) for v in topics.values())
print('Total words:', total)

lines = []
lines.append('package hung.edu.apptienganh.data.seed;')
lines.append('')
lines.append('import java.util.ArrayList;')
lines.append('import java.util.List;')
lines.append('')
lines.append('import hung.edu.apptienganh.data.local.entity.Vocabulary;')
lines.append('')
lines.append('final class A1VocabSeedHelper {')
lines.append('')
lines.append('    private A1VocabSeedHelper() {}')
lines.append('')
lines.append('    static void add(List<Vocabulary> list, String word, String ipaUk, String ipaUs, String type,')
lines.append('                    String meaningVi, String context, String exEn, String exVi,')
lines.append('                    String collocations, String mistake, String topic) {')
lines.append('        list.add(new Vocabulary(word, ipaUk, ipaUs, type, meaningVi, context, exEn, exVi,')
lines.append('                collocations, mistake, "A1", topic, false, 0));')
lines.append('    }')
lines.append('')
lines.append('    static List<Vocabulary> buildAll() {')
lines.append('        List<Vocabulary> list = new ArrayList<>();')

for topic, words in topics.items():
    method = 'add' + ''.join(p.capitalize() for p in topic.replace(' and ', ' ').split())
    method = method.replace(' ', '').replace('-', '')
    # simplify method names
    mapping = {
        'Daily life': 'DailyLife', 'Home and furniture': 'HomeFurniture',
        'Places in town': 'PlacesTown', 'Travel': 'Travel', 'Shopping': 'Shopping',
        'Weather': 'Weather', 'Health': 'Health', 'Feelings': 'Feelings',
        'Jobs': 'Jobs', 'Hobbies': 'Hobbies', 'Transportation': 'Transportation',
        'Basic technology': 'BasicTechnology'
    }
    mname = mapping[topic]
    lines.append(f'        add{mname}(list);')

lines.append('        return list;')
lines.append('    }')
lines.append('')

for topic, words in topics.items():
    mapping = {
        'Daily life': 'DailyLife', 'Home and furniture': 'HomeFurniture',
        'Places in town': 'PlacesTown', 'Travel': 'Travel', 'Shopping': 'Shopping',
        'Weather': 'Weather', 'Health': 'Health', 'Feelings': 'Feelings',
        'Jobs': 'Jobs', 'Hobbies': 'Hobbies', 'Transportation': 'Transportation',
        'Basic technology': 'BasicTechnology'
    }
    mname = mapping[topic]
    lines.append(f'    private static void add{mname}(List<Vocabulary> list) {{')
    for w in words:
        word, ipaUk, ipaUs, typ, meaning, ctx, exEn, exVi, coll, mistake = w
        def esc(s):
            return s.replace('\\', '\\\\').replace('"', '\\"')
        lines.append(f'        add(list, "{esc(word)}", "{esc(ipaUk)}", "{esc(ipaUs)}", "{esc(typ)}",')
        lines.append(f'                "{esc(meaning)}", "{esc(ctx)}", "{esc(exEn)}", "{esc(exVi)}",')
        lines.append(f'                "{esc(coll)}", "{esc(mistake)}", "{esc(topic)}");')
    lines.append('    }')
    lines.append('')

lines.append('}')
out = r'app\src\main\java\hung\edu\apptienganh\data\seed\A1VocabSeedHelper.java'
with open(out, 'w', encoding='utf-8') as f:
    f.write('\n'.join(lines))
print('Written', out)
