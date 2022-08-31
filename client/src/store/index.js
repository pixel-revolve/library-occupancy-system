import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:'',
    username: '',
    role:'',
    avatar:'',
    reservationTable:{
    },
    id:'',
    noLogin:true
  },
  mutations: {
    SET_TOKEN:(state,token)=>{
      state.token=token
      localStorage.setItem("token",token)
    },
    SET_USERNAME: (state,username)=>{
      state.username=username;
    },
    SET_ROLE:(state,role)=>{
      state.role=role;
    },
    SET_AVATAR:(state, avatar)=>{
      state.avatar=avatar;
    },
    SET_RESERVATIONTABLE:(state,[key,value])=>{
      state.reservationTable[key]=value;
    },
    SET_ID:(state,id)=>{
      state.id=id;
    },
    resetState: (state)=>{
      state.token=''
      state.username=''
      state.avatar=''
      state.role=''
      state.id=''
      state.noLogin=true
      state.reservationTable={}
    },
    resetReservation:(state)=>{
      state.reservationTable={}
    }

  },
  actions: {
  },
  modules: {
  }
})
