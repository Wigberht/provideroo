<script type="text/x-template" id="user-list-template">
    <div class="user-list">
        <h3>{{title}}</h3>
        <div v-for="user in users">
            <div class="row small-margin ">
                <div class="col s6 center offset-s3">
                    <div @click="createChat(user.id,user.login)"
                         class="card-panel blue lighten-3 small-padding">
                        <span>
                            {{user.login}}
                            <template v-if="user.admin">
                                <b>(admin)</b>
                            </template>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script>
    Vue.component('user-list', {
        template: "#user-list-template",
        data() {
            return {
                users: [1, 2, 3],
                chat: [],
                title: strings['list_of_users']
            }
        },
        methods: {
            fetchUsers() {
                axios.get("/rest/user/all")
                     .then((response) => {
                         console.log(response);
                         if (response.data.length > 0) {
                             this.users = response.data;
                         }
                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },
            createChat(userId, userLogin) {
                console.log("chat with " + userLogin)
                axios.post("/rest/chat/create", {
                    creatorId: window.user.id,
                    creatorLogin: window.user.login,
                    receiverId: userId,
                    receiverLogin: userLogin
                }).then((response) => {
                    console.log(response);
                    if (response.data) {
                        this.$emit("toChat", response.data.id);
                    }
                }).catch((error) => {
                    console.log(error);
                })

            }
        },
        mounted: function () {
            console.log("user list mounted");
            this.fetchUsers();
        },
        computed: {}
    })
</script>
