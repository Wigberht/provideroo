<script type="text/x-template" id="ban-button-template">
    <a @click="banToggle"
       class="waves-effect waves-light btn">{{buttonText}}</a>
</script>

<script>
    Vue.component('ban-button', {
        props: ['banned_state', 'user_id'],
        template: "#ban-button-template",
        data() {
            return {
                banned: false,
                toast: {
                    time: 1000,
                }
            }
        },
        methods: {
            banToggle() {
                axios.post("/rest/user/ban", {
                    'userId': this.user_id,
                    'banned': !this.banned
                }).then((response) => {
                    console.log(response);

                    if (response.data.success) {
                        this.banned = !this.banned;
                        Materialize.toast("Toggle success", this.toast.time)
                    } else {
                        Materialize.toast("Toggle fail", this.toast.time)
                    }
                }).catch((error) => {
                    console.log(error);
                    Materialize.toast("Toggle fail", this.toast.time);
                })
            },
        },
        mounted: function () {
            this.banned = this.banned_state == 'true';
            console.log(strings['access_denied_description']);
        },
        computed: {
            buttonText() {
                return this.banned ? strings['unban'] : strings['ban'];
            }
        }
    })
</script>
