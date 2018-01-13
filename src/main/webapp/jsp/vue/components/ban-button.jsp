<script type="text/x-template" id="ban-button-template">
    <a v-if="banned=='true'"
       class="waves-effect waves-light btn">{{text_unban}}</a>
    <a v-else @click="banUser"
       class="waves-effect waves-light btn">{{text_ban}}</a>
</script>

<script>
    Vue.component('ban-button', {
        props: ['text_ban', 'text_unban', 'banned', 'user_id'],
        template: "#ban-button-template",
        methods: {
            banUser() {
                console.log(this.user_id);
                axios.post("/rest/user/ban", {
                    'userId': this.user_id
                }).then((response) => {
                    console.log(response);
                }).catch((error) => {
                    console.log(error)
                })
            }
        },
        mounted: function () {
        }
    })
</script>
