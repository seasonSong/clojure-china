;;对于用户的个人信息的一起列操作方法
(ns clojure-china.dbutil.userdbutil
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-china.dbutil.dbconn :refer [db-spec]]
            [clj-time.local :as l])
  (:import  [java.sql Timestamp]
            [java.util Date]))

;;新建用户
(defn useradd! [user-map]
  (jdbc/insert! db/db-spec :cc_user
                user-map))
;;按id查询用户
(defn user-id-query [id]
  (jdbc/query db/db-spec :cc_user
              ["select * from cc_user where id = ?" id]))
;;按用户名查询用户
(defn user-name-query [username]
  (jdbc/query db/db-spec :cc_user
              ["select * from cc_user where username = ?" username]))
;;插入数据
(defn insert-user!
  [username encrypted-password email is-admin?]
  (jdbc/insert!
    db-spec :cc_user
    {:username username
     :password encrypted-password
     :email email
     :is_admin is-admin?
     :register_time (Timestamp. (.getTime (Date.)))}))
