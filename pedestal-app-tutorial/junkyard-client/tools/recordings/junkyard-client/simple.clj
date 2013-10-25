{:config {:order 0, :description "junk", :name :simple}
 :data
 [
  [:node-create [] :map]
  [:node-create [:greeting] :map]
  [:transform-enable [:greeting] :inc [{:io.pedestal.app.messages/topic [:counter]}]]
  [:node-create [:set-value] :map]
  [:transform-enable [:set-value] :button [{:io.pedestal.app.messages/type :set, :io.pedestal.app.messages/topic [:greeting]}]]
  [:node-create [:counter] :map]
  [:value [:counter] nil 2]
  :break
  [:value [:counter] 2 3]
  :break
  [:value [:counter] 3 4]
  :break
 ]}